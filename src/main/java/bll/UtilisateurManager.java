package bll;

import java.util.List;

import org.eclipse.tags.shaded.org.apache.bcel.verifier.exc.VerificationException;

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
	
	public void ajouterUtilisateur(Utilisateur utilisateur) throws BLLException {
		BLLException be = new BLLException();
		// tu créées une BLLException
		verification(utilisateur,be);
		//verification des champs (tu passes l'utilisateur et l'exception)
		if(be.hasErrors()) {
			throw be;
		}
		// si tu as des erreurs tu les fait remonter, sinon tu continues
		
		//bcrypt
		DAOFactory.getUtilisateurDAO().insert(utilisateur);
	}
	
	// ta fonction de verification des champs
	// ton erreur BLL => a chaque erreur tu la rajoute dans la List de BLLException
	// 
	private void verification(Utilisateur utilisateur, BLLException be) {
		
		
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
