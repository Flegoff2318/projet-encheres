package bll;


import java.util.List;


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

	public void addUser(Utilisateur utilisateur ) {
		// check fields
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
			throw new BLLException();
		}
		
//		BCrypt.Result result = BCrypt.verifyer().verify(motdepasse.toCharArray(), utilisateur.getMotDePasse());
//		if (!result.verified) {
//			throw new BLLException("Le mot de passe est erroné!");
//		}
		//creation session
		
		return utilisateur;
	} 

}
