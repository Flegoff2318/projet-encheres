package dal;

import java.util.List;

import bo.ArticleVendu;


public interface ArticleVenduDAO extends DAO<ArticleVendu> {
	void deleteArticlesUtilisateur(int noUtilisateur);
	List<ArticleVendu> selectSearch(int categorie, String recherche);
	List<ArticleVendu> selectKeyword(String recherche);
	
}
