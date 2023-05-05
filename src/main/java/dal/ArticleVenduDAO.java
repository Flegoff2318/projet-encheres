package dal;

import java.util.List;

import bo.ArticleVendu;


public interface ArticleVenduDAO extends DAO<ArticleVendu> {

	List<ArticleVendu> selectSearch(int categorie, String recherche);
	List<ArticleVendu> selectKeyword(String recherche);
	
}
