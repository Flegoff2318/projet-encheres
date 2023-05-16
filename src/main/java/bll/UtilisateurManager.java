package bll;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bo.Token;
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
		verifChamps(utilisateur,be);
		//verification des champs (tu passes l'utilisateur et l'exception)
		if(be.hasErrors()) {
			throw be;
		}
		// si tu as des erreurs tu les fait remonter, sinon tu continues
		
		//bcrypt
		DAOFactory.getUtilisateurDAO().insert(utilisateur);
	}

	public void supprimerUtilisateur(int id) {
		DAOFactory.getUtilisateurDAO().delete(id);;
	}
	
	public void modifierUtilisateur(Utilisateur utilisateur) throws BLLException {
		BLLException be = new BLLException();
		// tu créées une BLLException
		verifChamps(utilisateur,be);
		//verification des champs (tu passes l'utilisateur et l'exception)
		if(be.hasErrors()) {
			throw be;
		}
		DAOFactory.getUtilisateurDAO().update(utilisateur);
	}
	
	public List<Utilisateur> selectionnerUtilisateurs() {
		return DAOFactory.getUtilisateurDAO().selectAll();
	}
	
	public Utilisateur selectionnerUtilisateurParId(int id) {
		return DAOFactory.getUtilisateurDAO().selectById(id);
	}

	public Utilisateur selectionnerParToken(Token token){
		return  DAOFactory.getUtilisateurDAO().selectByToken(token);
	}


	public void verifChamps(Utilisateur utilisateur,BLLException be){
		String regexLettres = "^\\p{L}+$";
		String regexAlphanumeric = "^[a-zA-Z0-9]+$";
		// Regex numérique utilisé pour le téléphone, uniquement des chiffres, pas de tirets ni d'espaces.
		String regexNumeric = "^[0-9]+$";
		String regexMail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
		String regexPassword = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=])(?=\\S+$).{12,}$";

		Pattern pattern = Pattern.compile(regexLettres);

		// NOM
		Matcher matcher = pattern.matcher(utilisateur.getNom());
		if(utilisateur.getNom().length()>29 || !matcher.matches() || utilisateur.getNom().isEmpty()){
			be.ajouterErreur("Le nom doit être inférieur à 30, ne comporter que des lettres et est obligatoire.");
		}

		// PRENOM
		matcher = pattern.matcher(utilisateur.getPrenom());
		if(utilisateur.getPrenom().length()>29 || !matcher.matches() || utilisateur.getPrenom().isEmpty()){
			be.ajouterErreur("Le prénom doit être inférieur à 30, ne comporter que des lettres et est obligatoire.");
		}

		// VILLE
		matcher = pattern.matcher(utilisateur.getVille());
		if(utilisateur.getVille().length()>29 || !matcher.matches() || utilisateur.getVille().isEmpty()){
			be.ajouterErreur("La ville doit être inférieur à 30, ne comporter que des lettres et est obligatoire.");
		}

		// CODE POSTAL
		if(utilisateur.getCodePostal().length()>9 || utilisateur.getCodePostal().isEmpty()){
			be.ajouterErreur("Le code postal doit être inférieur à 10 et est obligatoire.");
		}

		// RUE
		if(utilisateur.getRue().length()>49 || utilisateur.getRue().isEmpty()){
			be.ajouterErreur("La rue doit être inférieur à 50 et est obligatoire.");
		}

		// TELEPHONE
		pattern = Pattern.compile(regexNumeric);
		matcher = pattern.matcher(utilisateur.getTelephone());
		if(utilisateur.getTelephone().length()>14 || !matcher.matches() || utilisateur.getTelephone().isEmpty()){
			be.ajouterErreur("Le numéro de téléphone doit être inférieur à 15, ne comporter que des chiffres et est obligatoire.");
		}

		// PSEUDO
		pattern = Pattern.compile(regexAlphanumeric);
		matcher = pattern.matcher(utilisateur.getPseudo());
		if(utilisateur.getPseudo().length()>29 || !matcher.matches() || utilisateur.getPseudo().isEmpty()){
			be.ajouterErreur("Le pseudo doit être inférieur à 30, ne comporter que des chiffres et des lettres et est obligatoire.");
		}

		// EMAIL
		pattern = Pattern.compile(regexMail);
		matcher = pattern.matcher(utilisateur.getEmail());
		if(utilisateur.getEmail().length()>99 || !matcher.matches() || utilisateur.getEmail().isEmpty()){
			be.ajouterErreur("L'adresse email doit être inférieur à 100, doit respecter le format 'xxxx@xxx.xxx' et est obligatoire.");
		}

		// MOT DE PASSE
		pattern = Pattern.compile(regexPassword);
		if(utilisateur.getMotDePasse().length()<12 || !matcher.matches() || utilisateur.getMotDePasse().isEmpty()){
			be.ajouterErreur("Le mot de passe doit contenir au moins une minuscule, une majuscule, un caractère spécial (@,#,$,%,^,&,+,=), un chiffre et comporter au minimum 12 caractères.");
		}
	}
}
