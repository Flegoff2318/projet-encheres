package bll;

import bo.Token;
import dal.DAOFactory;

public class TokenManager {
    private static TokenManager instance;

    private TokenManager() {
    }

    public static TokenManager getInstance() {
        if(instance==null) {
            instance = new TokenManager();
        }
        return instance;
    }

    public void ajouterToken(Token token){
        DAOFactory.getTokenDao().insert(token);
    }
    public void supprimerToken(int noUtilisateur){
        DAOFactory.getTokenDao().delete(noUtilisateur);
    }
}
