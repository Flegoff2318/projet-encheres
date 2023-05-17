package ihm;

import bll.*;
import bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

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
			supprimerCookies(utilisateur,request,response);
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

	public void supprimerCookies(Utilisateur utilisateur,HttpServletRequest request,HttpServletResponse response){
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie:cookies
		) {
			if(cookie.getName().equals("userToken") || cookie.getName().equals("passToken")){
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
		TokenManager.getInstance().supprimerToken(utilisateur.getNoUtilisateur());
	}
}
