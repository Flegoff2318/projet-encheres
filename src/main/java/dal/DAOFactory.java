package dal;

import dal.jdbc.ArticleVenduImpl;
import dal.jdbc.CategorieDAOImpl;
import dal.jdbc.EnchereDAOImpl;
import dal.jdbc.UtilisateurDAOImpl;

public class DAOFactory {

	public static UtilisateurDAOImpl getUtilisateurDAO() {
		return new UtilisateurDAOImpl();
	}
	
	public static EnchereDAOImpl getEnchereDAO() {
		return new EnchereDAOImpl();
	}
	
	public static CategorieDAOImpl getCategorieDAO() {
		return new CategorieDAOImpl();
	}
	
	public static ArticleVenduDAO getArticleVenduDao() {
		return  new ArticleVenduImpl();
	} 
}
