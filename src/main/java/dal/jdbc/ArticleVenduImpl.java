package dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bo.ArticleVendu;
import bo.Categorie;
import bo.Utilisateur;
import config.ConnectionProvider;
import dal.ArticleVenduDAO;

public class ArticleVenduImpl implements ArticleVenduDAO{

	private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS ("
			+ "nom_article,"
			+ "description,"
			+ "date_debut_encheres,"
			+ "date_fin_encheres,"
			+ "prix_initial,"
			+ "prix_vente,"
			+ "etat_vente,"
			+ "no_utilisateur,"
			+ "no_categorie"
			+ ") VALUES (?,?,?,?,?,?,?,?,?); ";
	
	private static final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?;";
	private static final String DELETE_ARTICLES_UTILISATEUR = "DELETE FROM ARTICLES_VENDUS WHERE no_utilisateur = ?;";
	private static final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS  SET ("
			+ "nom_article = ?,"
			+ "description = ?,"
			+ "date_debut_encheres = ?,"
			+ "date_fin_encheres = ?,"
			+ "prix_initial = ?,"
			+ "prix_vente = ?,"
			+ "etat_vente = ?,"
			+ "no_utilisateur = ?,"
			+ "no_categorie = ?"			
			+")  WHERE no_article = ?";

	private static final String SELECT_ALL_ARTICLE = 
			"SELECT no_article, nom_article, description, "
			+ "date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, a.no_utilisateur, "
			+ "a.no_categorie, etat_vente, pseudo, nom, prenom, email, "
			+ "telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur, "
			+ "libelle "
			+ "FROM ARTICLES_VENDUS a "
			+ "JOIN UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur "
			+ "JOIN CATEGORIES c ON a.no_categorie = c.no_categorie ";

	private static final String SELECT_SEARCH = 
			SELECT_ALL_ARTICLE
			+ "WHERE a.no_categorie = ? "
			+ "and nom_article like ?;";
	
	private static final String SELECT_KEYWORD = 
			SELECT_ALL_ARTICLE
			+ "WHERE nom_article like ?;";
	
	private static final String SELECT_SEARCH_USER = 
			SELECT_ALL_ARTICLE
			+ "WHERE a.no_categorie = ? "
			+ "and nom_article like ? "
			+ "and a.no_utilisateur = ?;";
	
	private static final String SELECT_KEYWORD_USER = 
			SELECT_ALL_ARTICLE
			+ "WHERE nom_article like ? "
			+ "and a.no_utilisateur = ?;";

	private static final String SELECT_ONE_ARTICLE = 
			SELECT_ALL_ARTICLE
			+ "WHERE no_article = ?";

	@Override
	public void insert(ArticleVendu articleVendu) {
		try(Connection connection = ConnectionProvider.getConnection()){
			
			PreparedStatement  pStmt = connection.prepareStatement(
											INSERT_ARTICLE,
											PreparedStatement.RETURN_GENERATED_KEYS
										);
			pStmt.setString(1, articleVendu.getNomArticle());
			pStmt.setString(2, articleVendu.getDescription());			
			pStmt.setDate(3,Date.valueOf(LocalDate.now()));
			pStmt.setDate(4,Date.valueOf(LocalDate.now()));
			pStmt.setInt(5, articleVendu.getMiseAPrix());
			pStmt.setInt(6, articleVendu.getPrixVente());
			pStmt.setInt(7, articleVendu.getEtatVente());
			pStmt.setInt(8, articleVendu.getUtilisateur().getNoUtilisateur());
			pStmt.setInt(9, articleVendu.getCategorie().getNoCategorie());
		
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			if(rs.next()) {
				articleVendu.setNoArticle(rs.getInt(1));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		try(Connection connection = ConnectionProvider.getConnection()){					
			PreparedStatement  stmt = connection.prepareStatement(DELETE_ARTICLE);
			stmt.setInt(1, id);
			stmt.executeUpdate();		
		}catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void update(ArticleVendu articleVendu) {
		try(Connection connection = ConnectionProvider.getConnection()){
			
			PreparedStatement  pStmt = connection.prepareStatement(UPDATE_ARTICLE);
			pStmt.setString(1, articleVendu.getNomArticle());
			pStmt.setString(2, articleVendu.getDescription());			
			pStmt.setDate(3,Date.valueOf(LocalDate.now()));
			pStmt.setDate(4,Date.valueOf(LocalDate.now()));
			pStmt.setInt(5, articleVendu.getMiseAPrix());
			pStmt.setInt(6, articleVendu.getPrixVente());
			pStmt.setInt(7, articleVendu.getEtatVente());
			pStmt.setInt(8, articleVendu.getUtilisateur().getNoUtilisateur());
			pStmt.setInt(9, articleVendu.getCategorie().getNoCategorie());	
			pStmt.executeUpdate();					
			
		}catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public List<ArticleVendu> selectAll() {

		try(Connection connection = ConnectionProvider.getConnection()){
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL_ARTICLE);
			return recupListeArticle(rs);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	

	@Override
	public ArticleVendu selectById(int id) {
		try(Connection connection = ConnectionProvider.getConnection()){
			
			PreparedStatement  pStmt = connection.prepareStatement(SELECT_ONE_ARTICLE);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()) {
				
				Utilisateur u = new Utilisateur();
				u.setNoUtilisateur(rs.getInt("no_utilisateur"));
				Categorie cat = new Categorie();
				cat.setNoCategorie(rs.getInt("no_categorie"));
				
				return new ArticleVendu(rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres").toLocalDate(),						
						rs.getDate("date_fin_encheres").toLocalDate(),
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						rs.getInt("etat_vente"),
						u,
						cat );			
			}		
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}

	@Override
	public void deleteArticlesUtilisateur(int noUtilisateur) {
		try (Connection connection = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = connection.prepareStatement(DELETE_ARTICLES_UTILISATEUR);
			pstmt.setInt(1,noUtilisateur);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<ArticleVendu> selectSearch(int categorie, String recherche) {
		try(Connection connection = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = connection.prepareStatement(SELECT_SEARCH);
			pstmt.setInt(1, categorie);
			pstmt.setString(2, "%"+recherche+"%");
			ResultSet rs = pstmt.executeQuery();
			return recupListeArticle(rs);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
		
	}
	
	public List<ArticleVendu> selectSearchUser(int categorie, String recherche, int noUtilisateur) {
		try(Connection connection = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = connection.prepareStatement(SELECT_SEARCH_USER);
			pstmt.setInt(1, categorie);
			pstmt.setString(2, "%"+recherche+"%");
			pstmt.setInt(3, noUtilisateur);
			ResultSet rs = pstmt.executeQuery();
			return recupListeArticle(rs);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
		
	}
	
	public List<ArticleVendu> selectKeyword(String recherche) {
		try(Connection connection = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = connection.prepareStatement(SELECT_KEYWORD);
			pstmt.setString(1, "%"+recherche+"%");
			ResultSet rs = pstmt.executeQuery();
			return recupListeArticle(rs);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
		
	}
	
	public List<ArticleVendu> selectKeywordUser(String recherche, int noUtilisateur) {
		try(Connection connection = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = connection.prepareStatement(SELECT_KEYWORD_USER);
			pstmt.setString(1, "%"+recherche+"%");
			pstmt.setInt(2, noUtilisateur);
			ResultSet rs = pstmt.executeQuery();
			return recupListeArticle(rs);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
		
	}
	
	private List<ArticleVendu> recupListeArticle(ResultSet rs) throws SQLException{
		List<ArticleVendu> articlesVendus = new ArrayList<>();
		while(rs.next()) {
			
			Utilisateur u = new Utilisateur(
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
			
			Categorie cat = new Categorie(
					rs.getInt("no_categorie"), 
					rs.getString("libelle")
					);
			
			articlesVendus.add( new ArticleVendu(rs.getInt("no_article"),
					rs.getString("nom_article"),
					rs.getString("description"),
					rs.getDate("date_debut_encheres").toLocalDate(),						
					rs.getDate("date_fin_encheres").toLocalDate(),
					rs.getInt("prix_initial"),
					rs.getInt("prix_vente"),
					rs.getInt("etat_vente"),
					u,
					cat					
					 ));						
		}
		return articlesVendus;
	}
}
