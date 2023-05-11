package dal.jdbc;

import bo.Token;
import config.ConnectionProvider;
import dal.TokenDAO;

import java.sql.*;
import java.util.List;

public class TokenDAOImpl implements TokenDAO {
    private final String INSERT = "INSERT INTO AUTH_TOKENS (no_utilisateur, user_token, password_token, date_expiration) VALUES (?,?,?,?);";
    private final String DELETE = "DELETE FROM AUTH_TOKENS WHERE no_utilisateur=?;";
    @Override
    public void insert(Token token) {
        try (Connection cnx = ConnectionProvider.getConnection()){
            PreparedStatement pstmt = cnx.prepareStatement(INSERT);
            pstmt.setInt(1,token.getUtilisateur().getNoUtilisateur());
            pstmt.setString(2,token.getUserToken());
            pstmt.setString(3,token.getPasswordToken());
            pstmt.setDate(4, Date.valueOf(token.getExpirationDate()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection cnx = ConnectionProvider.getConnection()){
            PreparedStatement pstmt = cnx.prepareStatement(DELETE);
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Token token) {
    }

    @Override
    public List<Token> selectAll() {
        return null;
    }

    @Override
    public Token selectById(int id) {
        return null;
    }
}
