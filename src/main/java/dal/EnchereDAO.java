package dal;

import java.util.List;

import bo.Enchere;

public interface EnchereDAO extends DAO<Enchere>{

    void supprimerAvecUtilisateur(int noUtilisateur);
	List<Enchere> selectByUtilisateur(int noUtilisateur);
	List<Enchere> selectByArticle(int noArticle);
	List<Enchere> selectSearch(int noCategorie, String recherche, int noUtilisateur);
	List<Enchere> selectKeyword(String recherche, int noUtilisateur);

}
