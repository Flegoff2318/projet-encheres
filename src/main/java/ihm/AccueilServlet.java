package ihm;

import bo.Utilisateur;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("")
public class AccueilServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	 HttpSession session = request.getSession();
         Utilisateur utilisateur = new Utilisateur();
         utilisateur.setPrenom("gilbert");
         session.setAttribute("utilisateur",utilisateur);
        
        request.getRequestDispatcher("/WEB-INF/encheres/accueil.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
