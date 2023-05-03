package dal;

import dal.jdbc.EnchereDAOImpl;
import dal.jdbc.UtilisateurDAOImpl;

public class DAOFactory {

	public static UtilisateurDAOImpl getUtilisateurDAO() {
		return new UtilisateurDAOImpl();
	}
	
	public static EnchereDAOImpl getEnchereDAO() {
		return new EnchereDAOImpl();
	}
	
}
