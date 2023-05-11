package ihm;

import bll.TokenManager;
import bo.Token;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import bll.BLLException;
import bll.SecurityService;
import bo.Utilisateur;



@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
	private final int DAY = 60*60*24;
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
			//Creation token
			if(request.getParameter("souvenir")!=null){
				Token token = new Token();
				token.setUtilisateur(utilisateur);
				addCookie("userToken",token.getUserToken(),response );
				addCookie("passToken",token.getPasswordToken(),response );
				TokenManager.getInstance().supprimerToken(utilisateur.getNoUtilisateur());
				TokenManager.getInstance().ajouterToken(token);
			}
			response.sendRedirect(request.getContextPath());
		} catch (BLLException e) {
			request.setAttribute("error", e.getMessages().toString());
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/encheres/connexion.jsp").forward(request, response);
		}
    }

	public void addCookie(String cookieName,String cookieValue,HttpServletResponse response){
		Cookie cookie = new Cookie(cookieName,cookieValue);
		cookie.setMaxAge(DAY);
		response.addCookie(cookie);
	}
}
