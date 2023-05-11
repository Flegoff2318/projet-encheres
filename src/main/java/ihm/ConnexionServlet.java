package ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import bll.BLLException;
import bll.SecurityService;
import bo.Utilisateur;

@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/encheres/connexion.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try {
			String login = request.getParameter("pseudo");
			//String email = request.getParameter("email");
			
			String motdepasse = request.getParameter("motdepasse");
			Utilisateur utilisateur = SecurityService.getInstance().login(login,motdepasse);
		
			//Creation session
			HttpSession session = request.getSession();
			session.setAttribute("utilisateur", utilisateur);
			response.sendRedirect(request.getContextPath());


		} catch (BLLException e) {
			request.setAttribute("error", e.getMessages().toString());
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/encheres/connexion.jsp").forward(request, response);
			
		}

    }
}
