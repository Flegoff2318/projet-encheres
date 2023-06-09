package dal.jdbc;

import bo.Token;
import bo.Utilisateur;
import dal.UtilisateurDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.ConnectionProvider;
import dal.UtilisateurDAO;

public class UtilisateurDAOImpl implements UtilisateurDAO {
	private final String SELECT_USER_BY_TOKEN = "select u.no_utilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur from UTILISATEURS u inner join AUTH_TOKENS t on u.no_utilisateur=t.no_utilisateur where t.user_token=? and t.password_token=?;";

	private final String INSERT = "INSERT INTO utilisateurs "
			+ "(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, "
			+ "mot_de_passe, credit, administrateur) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
	
	private final String DELETE = "DELETE FROM utilisateurs "
			+ "WHERE no_utilisateur = ?";	
	
	private final String UPDATE = "UPDATE utilisateurs "
			+ "SET pseudo = ?, nom = ?, prenom = ?, email = ?,"
			+ "telephone = ?, rue = ?, code_postal = ?, ville = ?, "
			+ "mot_de_passe = ?, credit = ?, administrateur = ? "
			+ "WHERE no_utilisateur = ?;";
	
	private final String SELECT_ALL = "SELECT * FROM utilisateurs;";
	
	private final String SELECT_BY_ID = "SELECT * FROM utilisateurs "
			+ "WHERE no_utilisateur = ?;";
	
	private static final String SELECT_BY_PSEUDO = "SELECT * FROM utilisateurs WHERE pseudo = ?";
	private static final String SELECT_BY_PSEUDO_OR_EMAIL = "SELECT * FROM utilisateurs WHERE pseudo = ? OR email = ?;";
	
	public Utilisateur selectByPseudo(String pseudo) {
		try(Connection connection = ConnectionProvider.getConnection()){
			PreparedStatement pStmt =  connection.prepareStatement(SELECT_BY_PSEUDO);
			pStmt.setString(1, pseudo);
			ResultSet rs =pStmt.executeQuery();
			if(rs.next())
			return new Utilisateur(
					rs.getInt("no_utilisateur"),
					rs.getString("pseudo"),
					rs.getString("nom"),
					rs.getString("prenom"),
					rs.getString("email"),
					rs.getString("telephone"),
					rs.getString("rue"),
					rs.getString("code_postal"),
					rs.getString("ville"),
					rs.getString("mot_de_passe"),
					rs.getInt("credit"),
					rs.getBoolean("administrateur")
					);
		}catch(SQLException e) {//DalException
			e.printStackTrace();
		}
		return null;
	}
	
	public Utilisateur selectByPseudoOrEmail(String pseudo, String email) {
		try(Connection connection = ConnectionProvider.getConnection()){
			PreparedStatement pStmt =  connection.prepareStatement(SELECT_BY_PSEUDO_OR_EMAIL);
			pStmt.setString(1, pseudo);
			pStmt.setString(2, email);
			ResultSet rs =pStmt.executeQuery();
			if(rs.next())
			return new Utilisateur(
					rs.getInt("no_utilisateur"),
					rs.getString("pseudo"),
					rs.getString("nom"),
					rs.getString("prenom"),
					rs.getString("email"),
					rs.getString("telephone"),
					rs.getString("rue"),
					rs.getString("code_postal"),
					rs.getString("ville"),
					rs.getString("mot_de_passe"),
					rs.getInt("credit"),
					rs.getBoolean("administrateur")
					);
		}catch(SQLException e) {//DalException
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void insert(Utilisateur utilisateur) {
		try (Connection connection = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setBoolean(11, utilisateur.isAdministrateur());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void delete(int id) {

		try (Connection connection = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = connection.prepareStatement(DELETE);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void update(Utilisateur utilisateur) {
		try (Connection connection = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = connection.prepareStatement(UPDATE);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setBoolean(11, utilisateur.isAdministrateur());
			pstmt.setInt(12, utilisateur.getNoUtilisateur());	
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Utilisateur> selectAll() {

		try (Connection connection = ConnectionProvider.getConnection()){
			
			List<Utilisateur> utilisateurs = new ArrayList<>();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			
			while(rs.next()) {
				utilisateurs.add(
						new Utilisateur(
								rs.getInt("no_utilisateur"), 
								rs.getString("pseudo"), 
								rs.getString("nom"), 
								rs.getString("prenom"), 
								rs.getString("email"), 
								rs.getString("telephone"), 
								rs.getString("rue"), 
								rs.getString("code_postal"), 
								rs.getString("ville"), 
								rs.getString("mot_de_passe"), 
								rs.getInt("credit"), 
								rs.getBoolean("administrateur")
								));
			}
			
			return utilisateurs;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Utilisateur selectById(int id) {

		try (Connection connection = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = connection.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();			
			if(rs.next()) {
						return new Utilisateur(
								rs.getInt("no_utilisateur"), 
								rs.getString("pseudo"), 
								rs.getString("nom"), 
								rs.getString("prenom"), 
								rs.getString("email"), 
								rs.getString("telephone"), 
								rs.getString("rue"), 
								rs.getString("code_postal"), 
								rs.getString("ville"), 
								rs.getString("mot_de_passe"), 
								rs.getInt("credit"), 
								rs.getBoolean("administrateur")
								);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}


	@Override
	public Utilisateur selectByToken(Token token) {
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER_BY_TOKEN);
			pstmt.setString(1, token.getUserToken());
			pstmt.setString(2,token.getPasswordToken());
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			if(rs.next()){
				return new Utilisateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8),rs.getString(9),rs.getString(10),rs.getInt(11), rs.getBoolean(12) );
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
}
