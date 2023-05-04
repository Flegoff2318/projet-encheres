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
			+ "no_utilisateur,"
			+ "no_categorie"
			+ ") VALUES (?,?,?,?,?,?,?,?); ";
	
	private static final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?;";

	private static final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS  SET ("
			+ "nom_article = ?,"
			+ "description = ?,"
			+ "date_debut_encheres = ?,"
			+ "date_fin_encheres = ?,"
			+ "prix_initial = ?,"
			+ "prix_vente = ?,"
			+ "no_utilisateur = ?,"
			+ "no_categorie = ?"			
			+")  WHERE no_article = ?";

	private static final String SELECT_ALL_ARTICLE = "SELECT * FROM ARTICLES_VENDUS";

	private static final String SELECT_ONE_ARTICLE = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?;";

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
				articleVendu.setNoArticle(1);
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
			List<ArticleVendu> articlesVendus = new ArrayList<>();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL_ARTICLE);
			while(rs.next()) {
				
				Utilisateur u = new Utilisateur();
				u.setNoUtilisateur(rs.getInt("noUtilisateur"));
				Categorie cat = new Categorie();
				cat.setNoCategorie(rs.getInt("noCategorie"));
				
				articlesVendus.add( new ArticleVendu(rs.getInt("noArticle"),
						rs.getString("nomArticle"),
						rs.getString("description"),
						rs.getDate("dateDebutEncheres").toLocalDate(),						
						rs.getDate("dateFinEncheres").toLocalDate(),
						rs.getInt("miseAPrix"),
						rs.getInt("prixVente"),
						rs.getInt("etatVente"),
						u,
						cat					
						 ) );						
			}
			return articlesVendus;
			
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
				u.setNoUtilisateur(rs.getInt("noUtilisateur"));
				Categorie cat = new Categorie();
				cat.setNoCategorie(rs.getInt("noCategorie"));
				
				return new ArticleVendu(rs.getInt("noArticle"),
						rs.getString("nomArticle"),
						rs.getString("description"),
						rs.getDate("dateDebutEncheres").toLocalDate(),						
						rs.getDate("dateFinEncheres").toLocalDate(),
						rs.getInt("miseAPrix"),
						rs.getInt("prixVente"),
						rs.getInt("etatVente"),
						u,
						cat );			
			}		
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
}
