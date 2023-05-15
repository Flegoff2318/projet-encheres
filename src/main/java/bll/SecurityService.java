package bll;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import at.favre.lib.crypto.bcrypt.BCrypt;

import bo.Utilisateur;
import dal.DAOFactory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityService {
	
	@Getter private static SecurityService instance = 
			new SecurityService();

	public void addUser(Utilisateur utilisateur ) throws BLLException {
		BLLException be = new BLLException();
		verifChamps(utilisateur,be);
		if(be.hasErrors()){
			throw be;
		}
		utilisateur.setMotDePasse( 
				BCrypt.withDefaults().hashToString(12, utilisateur.getMotDePasse().toCharArray())
				);
		DAOFactory.getUtilisateurDAO().insert(utilisateur);		
	}
	
	

	public Utilisateur login(String login, String motdepasse) throws BLLException {
		
		Utilisateur utilisateur = DAOFactory.getUtilisateurDAO().selectByPseudo(login);

		
		if(login == null) {
			throw new BLLException();
		}
		
		if(!motdepasse.equals(utilisateur.getMotDePasse())) {	
//			System.out.println("erreur ici");
//			throw new BLLException();
			BCrypt.Result result = BCrypt.verifyer().verify(motdepasse.toCharArray(), utilisateur.getMotDePasse());
			if (!result.verified) {
				throw new BLLException("Vos identifiants sont incorrects");
			}
		}
		
//		BCrypt.Result result = BCrypt.verifyer().verify(motdepasse.toCharArray(), utilisateur.getMotDePasse());
//		if (!result.verified) {
//			throw new BLLException("Vos identifiants sont incorrects");
//		}
		
		//creation session
		
		return utilisateur;
	}

	public void verifChamps(Utilisateur utilisateur,BLLException be){
		String regexLettres = "^[a-zA-Z]+$";
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
