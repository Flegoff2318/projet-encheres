package dal;

import bo.Token;
import bo.Utilisateur;

public interface UtilisateurDAO extends DAO<Utilisateur>{
    Utilisateur selectByToken(Token token);
}
