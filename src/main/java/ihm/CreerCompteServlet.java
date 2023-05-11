package ihm;

import bll.SecurityService;
import bll.UtilisateurManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import bo.Utilisateur;

@WebServlet("/creer-compte")
public class CreerCompteServlet extends HttpServlet {
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("/WEB-INF/encheres/creer-compte.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String pseudo = request.getParameter("pseudo");
    		String nom = request.getParameter("rue");
    		String prenom = request.getParameter("prenom");
    		String email = request.getParameter("email");
    		String telephone = request.getParameter("telephone");
			String rue = request.getParameter("rue");
    		String codepostal = request.getParameter("codepostal");
    		String ville = request.getParameter("ville");
    		String motDePasse = request.getParameter("motdepasse");
			String confirmation = request.getParameter("confirmation");
			if(motDePasse.equals(confirmation)){
				//BCrypt				
				Utilisateur nouvelUtilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codepostal, ville, motDePasse,false);
//				UtilisateurManager.getInstance().ajouterUtilisateur(nouvelUtilisateur);
				
				SecurityService.getInstance().addUser(nouvelUtilisateur);
				request.setAttribute("message","votre compte à bien été créé !");
				response.sendRedirect(request.getContextPath()+"/connexion");
			}else{
				request.setAttribute("erreur","le mot de passe ne correspond pas.");
				request.getRequestDispatcher("/WEB-INF/encheres/creer-compte.jsp").forward(request,response);
			}

    }
}
