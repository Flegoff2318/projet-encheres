package dal;

import dal.jdbc.ArticleVenduImpl;

public class DAOFactory {

	public static ArticleVenduDAO getArticleVenduDao() {
		return  new ArticleVenduImpl();
	} 
}
