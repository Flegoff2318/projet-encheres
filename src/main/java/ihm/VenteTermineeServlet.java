package ihm;

import bll.ArticleVenduManager;
import bll.BLLException;
import bll.EnchereManager;
import bll.RetraitManager;
import bo.ArticleVendu;
import bo.Enchere;
import bo.Retrait;
import bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
@WebServlet("/vente-terminee/*")
public class VenteTermineeServlet extends HttpServlet {
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int idArticle = Integer.parseInt(request.getPathInfo().substring(1));
		 
		 ArticleVendu article = ArticleVenduManager.getInstance().getArticleVendu(idArticle);
		 request.setAttribute("article",article);
		 
		 Enchere enchere = EnchereManager.getInstance().selectionnerMeilleureEnchere(idArticle);
		 request.setAttribute("enchere", enchere!=null?enchere.getMontant_enchere():0);
		 
		 Retrait retrait = RetraitManager.getInstance().selectionnerRetraitParNoArticle(idArticle);
		 request.setAttribute("retrait", retrait);
		 
		 request.getRequestDispatcher("/WEB-INF/encheres/vente-terminee.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		int idArticle = Integer.parseInt(request.getPathInfo().substring(1));		 
		ArticleVendu article = ArticleVenduManager.getInstance().getArticleVendu(idArticle);
		article.setEtatVente(3);
		try {
			ArticleVenduManager.getInstance().modifierArticle(article);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath());
		return;
	}

}

