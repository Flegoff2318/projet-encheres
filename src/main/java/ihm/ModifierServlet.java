package ihm;

import bll.UtilisateurManager;
import bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/profil/modifier/*")
public class ModifierServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/encheres/modifier-profil.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pseudo=request.getParameter("pseudo");
        String nom=request.getParameter("nom");
        String prenom=request.getParameter("prenom");
        String email=request.getParameter("email");
        String telephone=request.getParameter("telephone");
        String rue=request.getParameter("rue");
        String codePostal=request.getParameter("codepostal");
        String ville=request.getParameter("ville");
        String motDePasse=request.getParameter("motdepasse");
        String confirmation=request.getParameter("confirmation");
        HttpSession session = request.getSession();
        Utilisateur oldUser = (Utilisateur) session.getAttribute("utilisateur");
        if(motDePasse.equals(confirmation)){
            Utilisateur newUser = new Utilisateur(oldUser.getNoUtilisateur(),pseudo,nom,prenom,email,telephone,rue,codePostal,ville,motDePasse, oldUser.getCredit(), oldUser.isAdministrateur());
            UtilisateurManager.getInstance().modifierUtilisateur(newUser);
            session.setAttribute("utilisateur",newUser);
            response.sendRedirect(request.getContextPath());
        }else{
            request.setAttribute("erreur","Les mots de passe ne correspondent pas.");
            request.getRequestDispatcher("/WEB-INF/encheres/modifier-profil.jsp").forward(request,response);
        }
    }
}
