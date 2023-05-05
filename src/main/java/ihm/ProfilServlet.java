package ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import bll.UtilisateurManager;
import bo.Utilisateur;

@WebServlet("/profil/*")
public class ProfilServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int noUtilisateur = Integer.parseInt(request.getPathInfo().substring(1));
       Utilisateur utilisateur = UtilisateurManager.getInstance().selectionnerUtilisateurParId(noUtilisateur);
       request.setAttribute("utilisateur", utilisateur);
       request.getRequestDispatcher("/WEB-INF/encheres/profil.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
