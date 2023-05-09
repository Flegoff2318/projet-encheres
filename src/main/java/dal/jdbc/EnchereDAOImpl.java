package dal.jdbc;

import bo.Enchere;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import bo.ArticleVendu;
import bo.Categorie;
import bo.Utilisateur;
import config.ConnectionProvider;
import dal.EnchereDAO;

public class EnchereDAOImpl implements EnchereDAO{

	private final String INSERT = "INSERT INTO encheres "
			+ "(no_utilisateur, no_article, date_enchere, montant_enchere) "
			+ "VALUES (?,?,?,?);";
	//inutilisé
	private final String DELETE = "DELETE FROM encheres "
			+ "WHERE no_article = ?";
	private final String UPDATE = "UPDATE encheres "
			+ "SET date_enchere = ?, montant_enchere = ? "
			+ "WHERE no_utilisateur = ? AND no_article = ?;";
	
	private static final String SELECT_ALL = "SELECT "
			//article
			+ "a.no_article, nom_article, description, date_debut_encheres, "
			+ "date_fin_encheres, prix_initial, prix_vente, a.no_utilisateur, "
			+ "a.no_categorie, etat_vente, "
			//utilisateur1
			+ "u.pseudo, u.nom, u.prenom, u.email, u.telephone, u.rue, u.code_postal, "
			+ "u.ville, u.mot_de_passe, u.credit, u.administrateur, "
			//utilisateur2
			+ "u2.no_utilisateur as 'no_utilisateur2', u2.pseudo as 'pseudo2', "
			+ "u2.nom as 'nom2', u2.prenom as 'prenom2', u2.email as 'email2', "
			+ "u2.telephone as 'telephone2', u2.rue as 'rue2', "
			+ "u2.code_postal as 'code_postal2', u2.ville as 'ville2', "
			+ "u2.mot_de_passe as 'mot_de_passe2', u2.credit as 'credit2', "
			+ "u2.administrateur as 'administrateur2', "
			//categorie & encheres
			+ "libelle, e.date_enchere, e.montant_enchere "
			+ "FROM ARTICLES_VENDUS a "
			+ "JOIN UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur "
			+ "JOIN CATEGORIES c ON a.no_categorie = c.no_categorie "
			+ "JOIN ENCHERES e ON e.no_article = a.no_article "
			+ "JOIN UTILISATEURS u2 on u2.no_utilisateur = e.no_utilisateur ";
	
	private final static String SELECT_BY_UTILISATEUR = SELECT_ALL
			+ "WHERE e.no_utilisateur = ?;";
	private final static String SELECT_BY_ARTICLE = SELECT_ALL
			+ "WHERE e.no_article = ?;";
	
	private static final String SELECT_SEARCH = 
			SELECT_ALL
			+ "WHERE a.no_categorie = ? "
			+ "and nom_article like ? "
			+ "and e.no_utilisateur = ?;";
	
	private static final String SELECT_KEYWORD = 
			SELECT_ALL
			+ "WHERE nom_article like ? "
			+ "and e.no_utilisateur = ?;";
	
	private final String SELECT_BY_ID = "SELECT * FROM encheres "
			+ "WHERE no_article = ?;";
	
	@Override
	public void insert(Enchere enchere) {
		try (Connection connection = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = connection.prepareStatement(INSERT);
			pstmt.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(2, enchere.getArticleVendu().getNoArticle());
			pstmt.setObject(3, enchere.getDateEnchere());
			pstmt.setInt(4, enchere.getMontant_enchere());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Enchere enchere) {
		try (Connection connection = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = connection.prepareStatement(UPDATE);
			pstmt.setObject(1, enchere.getDateEnchere());
			pstmt.setInt(2, enchere.getMontant_enchere());
			pstmt.setInt(3, enchere.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(4, enchere.getArticleVendu().getNoArticle());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void delete(int id) {
		
	}
	
	@Override
	public List<Enchere> selectAll() {

		try (Connection connection = ConnectionProvider.getConnection()){
			
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);			
			return recupEncheres(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Enchere> selectByUtilisateur(int noUtilisateur){
		try (Connection connection = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = connection.prepareStatement(SELECT_BY_UTILISATEUR);
			pstmt.setInt(1, noUtilisateur);
			ResultSet rs = pstmt.executeQuery();			
			return recupEncheres(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Enchere> selectByArticle(int noArticle){
		try (Connection connection = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = connection.prepareStatement(SELECT_BY_ARTICLE);
			pstmt.setInt(1, noArticle);
			ResultSet rs = pstmt.executeQuery();			
			return recupEncheres(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Enchere> selectSearch(int noCategorie, String recherche, int noUtilisateur){
		try (Connection connection = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = connection.prepareStatement(SELECT_SEARCH);
			pstmt.setInt(1, noCategorie);
			pstmt.setString(2, "%"+recherche+"%");
			pstmt.setInt(3, noUtilisateur);
			ResultSet rs = pstmt.executeQuery();			
			return recupEncheres(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Enchere> selectKeyword(String recherche, int noUtilisateur){
		try (Connection connection = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = connection.prepareStatement(SELECT_KEYWORD);
			pstmt.setString(1, "%"+recherche+"%");
			pstmt.setInt(2, noUtilisateur);
			ResultSet rs = pstmt.executeQuery();			
			return recupEncheres(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Enchere selectById(int id) {

		try (Connection connection = ConnectionProvider.getConnection()){
			Enchere enchere = null;
			PreparedStatement pstmt = connection.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				enchere = new Enchere(
						rs.getTimestamp("date_enchere").toLocalDateTime(),
						rs.getInt("montant_enchere"),
						new Utilisateur(),
						new ArticleVendu());
				enchere.getArticleVendu().setNoArticle(rs.getInt("no_article"));
				enchere.getUtilisateur().setNoUtilisateur(rs.getInt("no_utilisateur"));
			}
			return enchere;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private List<Enchere> recupEncheres(ResultSet rs) throws SQLException{
		List<Enchere> encheres = new ArrayList<>();
		
		while(rs.next()) {
			Categorie categorie = new Categorie(
					rs.getInt("no_categorie"), 
					rs.getString("libelle"));
			Utilisateur vendeur = new Utilisateur(
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
					rs.getBoolean("administrateur"));
			Utilisateur acheteur = new Utilisateur(
					rs.getInt("no_utilisateur2"), 
					rs.getString("pseudo2"), 
					rs.getString("nom2"), 
					rs.getString("prenom2"), 
					rs.getString("email2"), 
					rs.getString("telephone2"), 
					rs.getString("rue2"), 
					rs.getString("code_postal2"), 
					rs.getString("ville2"), 
					rs.getString("mot_de_passe2"),
					rs.getInt("credit2"),
					rs.getBoolean("administrateur2"));
			ArticleVendu article = new ArticleVendu(
					rs.getInt("no_article"),
					rs.getString("nom_article"),
					rs.getString("description"), 
					rs.getDate("date_debut_encheres").toLocalDate(), 
					rs.getDate("date_fin_encheres").toLocalDate(), 
					rs.getInt("prix_initial"), 
					rs.getInt("prix_vente"), 
					rs.getInt("etat_vente"), 
					vendeur, 
					categorie);
			Enchere enchere = new Enchere(
					rs.getTimestamp("date_enchere").toLocalDateTime(),
					rs.getInt("montant_enchere"),
					acheteur,
					article);
			encheres.add(enchere);
		}
		return encheres;
	}
}
