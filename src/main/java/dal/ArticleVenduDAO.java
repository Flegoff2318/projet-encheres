package dal;


import java.util.List;
import bo.ArticleVendu;



public interface ArticleVenduDAO extends DAO<ArticleVendu> {
	void deleteArticlesUtilisateur(int noUtilisateur);
	List<ArticleVendu> selectSearch(int categorie, String recherche);
	List<ArticleVendu> selectKeyword(String recherche);
	List<ArticleVendu> selectSearchUser(int categorie, String recherche, int noUtilisateur);
	List<ArticleVendu> selectKeywordUser(String recherche, int noUtilisateur);
	

}
