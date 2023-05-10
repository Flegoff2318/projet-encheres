package dal;

import dal.jdbc.*;

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

	public static RetraitDAO getRetraitDao(){
		return new RetraitDAOImpl();
	}

	public static TokenDAO getTokenDao(){ return new TokenDAOImpl(); }
}
