package bll;

import java.util.List;

import bo.Utilisateur;
import dal.DAOFactory;

public class UtilisateurManager {
	
	private static UtilisateurManager instance;
	
	private UtilisateurManager() {
	}
	
	public static UtilisateurManager getInstance() {
		if(instance==null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}
	
	public void ajouterUtilisateur(Utilisateur utilisateur) {
		DAOFactory.getUtilisateurDAO().insert(utilisateur);
	}
	
	public void supprimerUtilisateur(int id) {
		DAOFactory.getUtilisateurDAO().delete(id);;
	}
	
	public void modifierUtilisateur(Utilisateur utilisateur) {
		DAOFactory.getUtilisateurDAO().update(utilisateur);
	}
	
	public List<Utilisateur> selectionnerUtilisateurs() {
		return DAOFactory.getUtilisateurDAO().selectAll();
	}
	
	public Utilisateur selectionnerUtilisateurParId(int id) {
		return DAOFactory.getUtilisateurDAO().selectById(id);
	}
}
