package dal.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bo.ArticleVendu;
import bo.Categorie;
import bo.Retrait;
import bo.Utilisateur;
import config.ConnectionProvider;
import dal.RetraitDAO;


public class RetraitDAOImpl implements RetraitDAO {
	private final String INSERT = "INSERT INTO RETRAITS (no_article, rue, code_postal, ville) VALUES (?,?,?,?);";
	private final String DELETE_RETRAITS_UTILISATEUR = "DELETE FROM RETRAITS " +
			"WHERE EXISTS (" +
			"              SELECT 1" +
			"              FROM ARTICLES_VENDUS" +
			"              WHERE no_utilisateur = ?" +
			"                AND no_article = RETRAITS.no_article" +
			"          );";
	private final String SELECT_ALL = "SELECT R.rue,R.code_postal,R.ville," +
			"U.no_utilisateur,U.pseudo,nom,prenom,email,telephone,U.rue,U.code_postal,U.ville,mot_de_passe,credit,administrateur," +
			"C.no_categorie,C.libelle,\n" +
			"AV.no_article,AV.nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,etat_vente" +
			"FROM RETRAITS R" +
			"JOIN ARTICLES_VENDUS AV on AV.no_article = R.no_article " +
			"JOIN UTILISATEURS U on U.no_utilisateur = AV.no_utilisateur " +
			"JOIN CATEGORIES C on C.no_categorie = AV.no_categorie;";
	private final String SELECT_BY_ID = "SELECT R.rue,R.code_postal,R.ville," +
			"U.no_utilisateur,U.pseudo,nom,prenom,email,telephone,U.rue,U.code_postal,U.ville,mot_de_passe,credit,administrateur," +
			"C.no_categorie,C.libelle," +
			"AV.no_article,AV.nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,etat_vente" +
			"FROM RETRAITS R" +
			"JOIN ARTICLES_VENDUS AV on AV.no_article = R.no_article " +
			"JOIN UTILISATEURS U on U.no_utilisateur = AV.no_utilisateur " +
			"JOIN CATEGORIES C on C.no_categorie = AV.no_categorie" +
			"WHERE AV.no_article=?;";
	private final String DELETE = "DELETE FROM RETRAITS WHERE no_article=?;";
	private final String UPDATE = "UPDATE RETRAITS SET rue=?,code_postal=?,ville=? WHERE no_article=?;";

	@Override
	public void insert(Retrait retrait) {
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(INSERT);
			pstmt.setInt(1,retrait.getArticle().getNoArticle());
			pstmt.setString(2,retrait.getRue());
			pstmt.setString(2,retrait.getCode_postal());
			pstmt.setString(2,retrait.getVille());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(int noArticle) {
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1,noArticle);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Retrait retrait) {
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
			pstmt.setString(1, retrait.getRue());
			pstmt.setString(2, retrait.getCode_postal());
			pstmt.setString(3, retrait.getCode_postal());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Retrait> selectAll() {
		try (Connection cnx = ConnectionProvider.getConnection()){
			Statement stmt = cnx.createStatement();
			List<Retrait> retraits = new ArrayList<>();
			stmt.executeQuery(SELECT_ALL);
			ResultSet rs = stmt.getResultSet();
			while(rs.next()){
				Utilisateur utilisateur = new Utilisateur(
						rs.getInt(4),//no util
						rs.getString(5),//pseudo
						rs.getString(6),//nom
						rs.getString(7),//prenom
						rs.getString(8),//email
						rs.getString(9),//telephone
						rs.getString(10),//rue
						rs.getString(11),//code postal
						rs.getString(12),//ville
						"",
						rs.getInt(14),//credit
						rs.getBoolean(15)//administrateur
				);
				Categorie categorie = new Categorie(
					rs.getInt(16),
						rs.getString(17)
				);
				ArticleVendu articleVendu = new ArticleVendu(
						rs.getInt(18),
						rs.getString(19),
						rs.getString(20),
						rs.getDate(21).toLocalDate(),
						rs.getDate(22).toLocalDate(),
						rs.getInt(23),
						rs.getInt(24),
						rs.getInt(25),
						utilisateur,
						categorie
				);
				retraits.add(new Retrait(articleVendu,rs.getString(1),rs.getString(2),rs.getString(3)));
			}
			if(!retraits.isEmpty()){
				return retraits;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public Retrait selectById(int noArticle) {
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1,noArticle);
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			if(rs.next()){
				Utilisateur utilisateur = new Utilisateur(
						rs.getInt(4),//no util
						rs.getString(5),//pseudo
						rs.getString(6),//nom
						rs.getString(7),//prenom
						rs.getString(8),//email
						rs.getString(9),//telephone
						rs.getString(10),//rue
						rs.getString(11),//code postal
						rs.getString(12),//ville
						"",
						rs.getInt(14),//credit
						rs.getBoolean(15)//administrateur
				);
				Categorie categorie = new Categorie(
						rs.getInt(16),
						rs.getString(17)
				);
				ArticleVendu articleVendu = new ArticleVendu(
						rs.getInt(18),
						rs.getString(19),
						rs.getString(20),
						rs.getDate(21).toLocalDate(),
						rs.getDate(22).toLocalDate(),
						rs.getInt(23),
						rs.getInt(24),
						rs.getInt(25),
						utilisateur,
						categorie
				);
				return new Retrait(articleVendu,rs.getString(1),rs.getString(2),rs.getString(3));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public void supprimerRetraitUtilisateur(int noUtilisateur) {
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_RETRAITS_UTILISATEUR);
			pstmt.setInt(1,noUtilisateur);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
