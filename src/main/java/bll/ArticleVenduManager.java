package bll;


import java.util.List;

import bo.ArticleVendu;
import dal.DAO;

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
		return DAO.getArticleVenduDao().selectAll();			
	}
	
	/** récup un article vendu**/
	public ArticleVendu getArticleVendu(int id) {
		return DAO.getArticleVenduDao().selectById(id);
	}	
	
	public void addArticle(ArticleVendu articleVendu) throws BLLException {
		checkArticle(articleVendu);
		DAO.getArticleVenduDao().insert(articleVendu);
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
}
