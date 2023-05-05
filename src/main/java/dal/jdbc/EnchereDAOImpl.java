package dal.jdbc;

import bo.Enchere;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.ArticleVendu;
import bo.Utilisateur;
import config.ConnectionProvider;
import dal.EnchereDAO;

public class EnchereDAOImpl implements EnchereDAO{

	private final String INSERT = "INSERT INTO encheres "
			+ "(no_utilisateur, no_article, date_enchere, montant_enchere) "
			+ "VALUES (?,?,?,?);";
	//inutilisé
	private final String DELETE_AVEC_UTILISATEUR = "DELETE FROM encheres "
			+ " WHERE no_utilisateur = ?";
	private final String DELETE_AVEC_ARTICLE = "DELETE FROM encheres " +
			" WHERE no_article " +
			" IN (select no_article from ARTICLES_VENDUS where ARTICLES_VENDUS.no_utilisateur = ?);";
	private final String UPDATE = "UPDATE encheres "
			+ "SET date_enchere = ?, montant_enchere = ? "
			+ "WHERE no_utilisateur = ? AND no_article = ?;";
	
	private final String SELECT_ALL = "SELECT * FROM encheres";
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
		try (Connection connection = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = connection.prepareStatement(DELETE_AVEC_ARTICLE);
			pstmt.setInt(1,id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Enchere> selectAll() {

		try (Connection connection = ConnectionProvider.getConnection()){
			List<Enchere> encheres = new ArrayList<>();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while(rs.next()) {
				Enchere enchere = new Enchere(
						rs.getTimestamp("date_enchere").toLocalDateTime(),
						rs.getInt("montant_enchere"),
						new Utilisateur(),
						new ArticleVendu());
				enchere.getArticleVendu().setNoArticle(rs.getInt("no_article"));
				enchere.getUtilisateur().setNoUtilisateur(rs.getInt("no_utilisateur"));
				encheres.add(enchere);
			}
			return encheres;
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

	@Override
	public void supprimerAvecUtilisateur(int noUtilisateur) {
		try (Connection connection = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = connection.prepareStatement(DELETE_AVEC_UTILISATEUR);
			pstmt.setInt(1,noUtilisateur);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
