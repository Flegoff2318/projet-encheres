package bll;

import bo.Retrait;
import dal.DAOFactory;

import java.util.List;

public class RetraitManager {
    private static RetraitManager instance;

    private RetraitManager() {
    }

    public static RetraitManager getInstance() {
        if(instance==null) {
            instance = new RetraitManager();
        }
        return instance;
    }

    public List<Retrait> selectionnerTousLesRetraits(){
        return DAOFactory.getRetraitDao().selectAll();
    }
    public Retrait selectionnerRetraitParNoArticle(int noArticle){
        return DAOFactory.getRetraitDao().selectById(noArticle);
    }
    public void supprimerUnRetrait(int noArticle){
        DAOFactory.getRetraitDao().delete(noArticle);
    }
    public void mettreAJourRetrait(Retrait retrait){
        DAOFactory.getRetraitDao().update(retrait);
    }
    public void ajouterRetrait(Retrait retrait){
        DAOFactory.getRetraitDao().insert(retrait);
    }
    public void supprimerRetraitsUtilisateur(int noUtilisateur){
        DAOFactory.getRetraitDao().supprimerRetraitUtilisateur(noUtilisateur);
    }
}
