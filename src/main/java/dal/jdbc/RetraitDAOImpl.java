package dal.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bo.Retrait;
import config.ConnectionProvider;
import dal.RetraitDAO;


public class RetraitDAOImpl implements RetraitDAO {
	private final String INSERT = "INSERT INTO RETRAITS (no_article, rue, code_postal, ville) VALUES (?,?,?,?);";
	private final String SELECT_ALL = "SELECT * FROM RETRAITS;";
	private final String SELECT_BY_ID = "SELECT * FROM RETRAITS WHERE no_article=?;";
	private final String DELETE = "DELETE FROM RETRAITS WHERE no_article=?;";
	private final String UPDATE = "UPDATE RETRAITS SET rue=?,code_postal=?,ville=? WHERE no_article=?;";

	@Override
	public void insert(Retrait retrait) {
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(INSERT);
			pstmt.setInt(1,retrait.getNoArticle());
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
				retraits.add(new Retrait(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
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
				return new Retrait(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

}
