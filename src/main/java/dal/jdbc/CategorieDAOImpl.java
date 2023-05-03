package dal.jdbc;

import bo.Categorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.ConnectionProvider;

import dal.CategorieDAO;

public class CategorieDAOImpl implements CategorieDAO{

	private final String SELECT_ALL = "SELECT * FROM categories;";
	private final String SELECT_BY_ID = "SELECT * FROM categories "
			+ "WHERE no_categorie = ?;";	

	@Override
	public List<Categorie> selectAll() {

		try (Connection connection = ConnectionProvider.getConnection()){
			
			List<Categorie> categories = new ArrayList<>();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while(rs.next()) {
				categories.add(new Categorie(
						rs.getInt("no_categorie"), 
						rs.getString("libelle")));
			}
			return categories;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Categorie selectById(int id) {

		try (Connection connection = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = connection.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				return new Categorie(
						rs.getInt("no_categorie"), 
						rs.getString("libelle"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
