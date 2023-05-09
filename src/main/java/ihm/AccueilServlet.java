package ihm;

import bo.ArticleVendu;
import bo.Categorie;
import bo.Utilisateur;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

import bll.ArticleVenduManager;
import bll.CategorieManager;

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
    	
    	int categorie = Integer.parseInt(request.getParameter("categorie"));
    	String recherche = request.getParameter("recherche");
    	
    	List<Categorie> categories = CategorieManager.getInstance().selectionnerCategories();
    	List<ArticleVendu> articles = ArticleVenduManager.choixListe(categorie, recherche);
    	request.setAttribute("categories", categories);
    	request.setAttribute("articles", articles);
    	request.getRequestDispatcher("/WEB-INF/encheres/accueil.jsp").forward(request,response);
    }
}
