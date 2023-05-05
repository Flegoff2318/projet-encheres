package ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.io.IOException;

import bll.BLLException;
import bll.SecurityService;
import bo.Utilisateur;

@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	 /*HttpSession session = request.getSession();
         Utilisateur utilisateur = new Utilisateur();
         utilisateur.setPrenom("gilbert");
         session.setAttribute("utilisateur",utilisateur);*/
        System.out.println("servlet connexion");
        request.getRequestDispatcher("/WEB-INF/encheres/connexion.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try {
			String login = request.getParameter("email");
			String motdepasse = request.getParameter("motdepasse");
			Utilisateur utilisateur = SecurityService.getInstance().Login(login,motdepasse);
			
			//Creation session
			HttpSession session = request.getSession();
			session.setAttribute("utilisateur",utilisateur);
			response.sendRedirect(request.getContextPath()+"/test2");
		} catch (BLLException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("connected");
    }
}
