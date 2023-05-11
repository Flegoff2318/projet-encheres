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
@WebServlet("/encherir/*")
public class EncherirServlet extends HttpServlet {
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int idArticle = Integer.parseInt(request.getPathInfo().substring(1));
		 
		 ArticleVendu article = ArticleVenduManager.getInstance().getArticleVendu(idArticle);
		 request.setAttribute("article",article);
		 
		 Enchere enchere = EnchereManager.getInstance().selectionnerMeilleureEnchere(idArticle);
		 request.setAttribute("enchere", enchere!=null?enchere.getMontant_enchere():0);
		 
		 Retrait retrait = RetraitManager.getInstance().selectionnerRetraitParNoArticle(idArticle);
		 request.setAttribute("retrait", retrait);
		 
		 request.getRequestDispatcher("/WEB-INF/encheres/encherir.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int proposition = Integer.parseInt(request.getParameter("proposition"));		
		int idArticle = Integer.parseInt(request.getPathInfo().substring(1));	 
		ArticleVendu article = ArticleVenduManager.getInstance().getArticleVendu(idArticle);		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		Enchere enchere = new Enchere(LocalDateTime.now(), proposition, utilisateur, article);
		
		if(utilisateur==null) {
			response.sendRedirect(request.getContextPath()+"/connexion");
			return;
		}		
		if(utilisateur.getNoUtilisateur()!=article.getUtilisateur().getNoUtilisateur()
				&& proposition > article.getPrixVente()
				&& article.getEtatVente()==1) 
		{
			EnchereManager.getInstance().ajouterEnchere(enchere);
			try {
				article.setPrixVente(proposition);
				ArticleVenduManager.getInstance().modifierArticle(article);
				request.setAttribute("succes", "l'enchere à bien été créée");
			} catch (BLLException e) {
				request.setAttribute("erreur", "l'enchere n'a pas pu etre créée");
				//TODO
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath());
			return;
		}

	}

}
