package bll;


import java.util.List;


import bo.ArticleVendu;

import dal.DAOFactory;

public class ArticleVenduManager {
	
	/** start Singleton */	
	// étape 1
	private static ArticleVenduManager instance;
	// étape 2
	private ArticleVenduManager() {}
	// étape 3
	public static ArticleVenduManager getInstance() {		
		if( instance == null) {
			instance = new ArticleVenduManager();
		}
		return instance;
	}
	/** end Singleton */
	
	/**  get all articles vendus  **/
	public List<ArticleVendu> getAllArticleVendus() {
		return DAOFactory.getArticleVenduDao().selectAll();			
	}
	
	/** récup un article vendu**/
	public ArticleVendu getArticleVendu(int id) {
		return DAOFactory.getArticleVenduDao().selectById(id);
	}	
	
	public void addArticle(ArticleVendu articleVendu) throws BLLException {
		checkArticle(articleVendu);
		DAOFactory.getArticleVenduDao().insert(articleVendu);
	}
	public void modifierArticle(ArticleVendu articleVendu) throws BLLException {
		checkArticle(articleVendu);
		DAOFactory.getArticleVenduDao().update(articleVendu);
	}
	
	public void checkArticle(ArticleVendu articleVendu ) throws BLLException {
		BLLException bll = new BLLException();
		checkFiled(articleVendu.getNomArticle(), "Nom de l'article",bll);		
		checkFiled(articleVendu.getDescription(), "Description",bll);
		//TODO voir en toString
		//checkFiled(toString(articleVendu.getMiseAPrix()), "Mise à prix",bll);		
		//checkFiled(articleVendu.getPrixVente(), "Prix de vente",bll);	
		
		if(bll.getErrors().size()>0) {			
			throw bll;
		}
	}
	
	private void checkFiled(String field,String name,BLLException bll ) {
		if(name.isBlank()) {
			bll.ajouterErreur("Le champs %s ne peut pas etre vide!".formatted(name));
		}
	}
	public List<ArticleVendu> listeArticlesRecherche(int categorie, String recherche) {
		return DAOFactory.getArticleVenduDao().selectSearch(categorie, recherche);		
	}
	public static List<ArticleVendu> choixListe(int categorie, String recherche) {
		if(categorie ==0) {
			return DAOFactory.getArticleVenduDao().selectKeyword(recherche);
		}
		else {
			return DAOFactory.getArticleVenduDao().selectSearch(categorie, recherche);
		}
	}

	public void supprimerArticlesUtilisateur(int noUtilisateur) {
		DAOFactory.getArticleVenduDao().deleteArticlesUtilisateur(noUtilisateur);
	}
	
	public List<ArticleVendu> choixListe(int categorie, String recherche, int noUtilisateur){
		if(categorie==0) {
			return DAOFactory.getArticleVenduDao().selectKeywordUser(recherche, noUtilisateur);
		}
		else {
			return DAOFactory.getArticleVenduDao().selectSearchUser(categorie, recherche, noUtilisateur);
		}

	}
}
