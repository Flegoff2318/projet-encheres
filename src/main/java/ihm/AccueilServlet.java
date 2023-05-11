package ihm;

import bo.ArticleVendu;
import bo.Categorie;
import bo.Enchere;
import bo.Utilisateur;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

import bll.ArticleVenduManager;
import bll.CategorieManager;
import bll.EnchereManager;

@WebServlet("")
public class AccueilServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	List<ArticleVendu> articles = ArticleVenduManager.getInstance().getAllArticleVendus();
    	List<Categorie> categories = CategorieManager.getInstance().selectionnerCategories();
    	request.setAttribute("categories", categories);
    	request.setAttribute("articles", articles);
        request.getRequestDispatcher("/WEB-INF/encheres/accueil.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	fonctionRecherche(request);
    	request.getRequestDispatcher("/WEB-INF/encheres/accueil.jsp").forward(request,response);
    }
    
    private void fonctionRecherche(HttpServletRequest request) {
    	
    	int categorie = Integer.parseInt(request.getParameter("categorie"));
    	String recherche = request.getParameter("recherche");
    	int noUtilisateur=0;
    	if(request.getSession().getAttribute("utilisateur")!=null) {   		
    		noUtilisateur = ((Utilisateur)request.getSession().
    				getAttribute("utilisateur")).getNoUtilisateur();
    	}
    	
    	boolean encheresOuvertes = request.getParameter("encheres-ouvertes")!=null?true:false;
    	boolean encheresEnCours = request.getParameter("encheres-en-cours")!=null?true:false;
    	boolean encheresRemportees = request.getParameter("encheres-remportees")!=null?true:false;
    	boolean ventesEnCours = request.getParameter("ventes-en-cours")!=null?true:false;
    	boolean ventesNonDebutees = request.getParameter("ventes-non-debutees")!=null?true:false;
    	boolean ventesTerminees = request.getParameter("ventes-terminees")!=null?true:false;
    	String type = request.getParameter("type");
    	
    	request.setAttribute("encheresOuvertes", encheresOuvertes);
    	request.setAttribute("encheresEnCours", encheresEnCours);
    	request.setAttribute("encheresRemportees", encheresRemportees);
    	request.setAttribute("ventesEnCours", ventesEnCours);
    	request.setAttribute("ventesNonDebutees", ventesNonDebutees);
    	request.setAttribute("ventesTerminees", ventesTerminees);
    	request.setAttribute("categorieChoisie", categorie);
    	request.setAttribute("rechercheChoisie", recherche);
    	request.setAttribute("type", type);
    	
    	if(encheresOuvertes || request.getSession().getAttribute("utilisateur")==null) {
    		List<ArticleVendu> articles = ArticleVenduManager.choixListe(categorie, recherche);
    		request.setAttribute("articles", articles);
    	}
    	if((encheresEnCours || encheresRemportees) && noUtilisateur!=0) {
    		List<Enchere> encheresUtilisateur = 
    				EnchereManager.getInstance().
    				choixListe(categorie, recherche, noUtilisateur);
    		request.setAttribute("encheresUtilisateur", encheresUtilisateur);
    	}
    	if((ventesEnCours || ventesNonDebutees || ventesTerminees) && noUtilisateur!=0) {
    		List<ArticleVendu> ventesUtilisateur = 
    				ArticleVenduManager.getInstance().
    				choixListe(categorie, recherche, noUtilisateur);
    		request.setAttribute("ventesUtilisateur", ventesUtilisateur);
    	}
    	
    	List<Categorie> categories = CategorieManager.getInstance().selectionnerCategories();
    	request.setAttribute("categories", categories);
    }
    
}
