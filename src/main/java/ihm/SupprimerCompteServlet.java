package ihm;

import bll.ArticleVenduManager;
import bll.EnchereManager;
import bll.RetraitManager;
import bll.UtilisateurManager;
import bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet("/profil/supprimer")
public class SupprimerCompteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		if(utilisateur!=null){
			int noUtilisateur = utilisateur.getNoUtilisateur();
			supprimerUtilisateur(noUtilisateur);
			session.invalidate();
		}
		response.sendRedirect(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Supprime tout ce qui est lié a un utilisateur.
	 * [ retraits | Enchères | Articles | Utilisateur ]
	 * @param noUtilisateur
	 */
	private void supprimerUtilisateur(int noUtilisateur){
		RetraitManager.getInstance().supprimerRetraitsUtilisateur(noUtilisateur);
		EnchereManager.getInstance().supprimerEnchereParArticle(noUtilisateur);
		EnchereManager.getInstance().supprimerEnchereParUtilisateur(noUtilisateur);
		ArticleVenduManager.getInstance().supprimerArticlesUtilisateur(noUtilisateur);
		UtilisateurManager.getInstance().supprimerUtilisateur(noUtilisateur);
	}
}
