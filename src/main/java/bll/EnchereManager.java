package bll;

import java.util.List;

import bo.Enchere;
import dal.DAOFactory;

public class EnchereManager {
	
	private static EnchereManager instance;
	
	private EnchereManager() {
	}
	
	public static EnchereManager getInstance() {
		if(instance==null) {
			instance = new EnchereManager();
		}
		return instance;
	}
	
	public void ajouterEnchere(Enchere enchere) {			
		DAOFactory.getEnchereDAO().insert(enchere);
	}

	/**
	 * supprime les enchères sur les articles de l'utilisateur concerné
	 * @param id
	 */
	public void supprimerEnchereParArticle(int noUtilisateur) {
		DAOFactory.getEnchereDAO().delete(noUtilisateur);
	}

	/**
	 * Supprime les enchères d'un utilisateur sur les articles concernés
	 * @param noUtilisateur
	 */
	public void supprimerEnchereParUtilisateur(int noUtilisateur) {
		DAOFactory.getEnchereDAO().supprimerAvecUtilisateur(noUtilisateur);
	}
	
	public void modifierEnchere(Enchere enchere) {
		DAOFactory.getEnchereDAO().update(enchere);
	}
	
	public List<Enchere> selectionnerEncheres() {
		return DAOFactory.getEnchereDAO().selectAll();
	}
	
	public List<Enchere> selectionnerEncheresArticle(int noArticle) {
		return DAOFactory.getEnchereDAO().selectByArticle(noArticle);
	}
	public Enchere selectionnerMeilleureEnchere(int noArticle) {
		return DAOFactory.getEnchereDAO().selectTopEnchere(noArticle);
	}
	
	public List<Enchere> selectionnerEncheresUtilisateur(int noUtilisateur) {
		return DAOFactory.getEnchereDAO().selectByUtilisateur(noUtilisateur);
	}
	public List<Enchere> selectionnerEncheresRecherche(int noCategorie, String recherche, int noUtilisateur) {
		return DAOFactory.getEnchereDAO().selectSearch(noCategorie, recherche, noUtilisateur);
	}
	public List<Enchere> selectionnerEncheresMotCle(String recherche, int noUtilisateur) {
		return DAOFactory.getEnchereDAO().selectKeyword(recherche, noUtilisateur);
	}
	
	public Enchere selectionnerEnchereParId(int id) {
		return DAOFactory.getEnchereDAO().selectById(id);
	}
	
	public List<Enchere> choixListe(int categorie, String recherche, int noUtilisateur) {
		if(categorie ==0) {
			return DAOFactory.getEnchereDAO().selectKeyword(recherche, noUtilisateur);
		}
		else {
			return DAOFactory.getEnchereDAO().selectSearch(categorie, recherche, noUtilisateur);
		}
	}
}
