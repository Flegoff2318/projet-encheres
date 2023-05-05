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
	
	public Enchere selectionnerEnchereParId(int id) {
		return DAOFactory.getEnchereDAO().selectById(id);
	}
}
