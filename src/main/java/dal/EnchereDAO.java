package dal;

import bo.Enchere;

public interface EnchereDAO extends DAO<Enchere>{
    void supprimerAvecUtilisateur(int noUtilisateur);
}
