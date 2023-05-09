package ihm;

import java.io.IOException;


import bo.Utilisateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@WebServlet("/test2")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		if(utilisateur == null) {
			response.sendRedirect(request.getContextPath()+"/connexion");
			return;
		}
		response.getWriter().append("Bonjour"+utilisateur.getPseudo() );		
		System.out.println("je suis connecté");
		
		
//		try {
//			ConnectionProvider.getConnection();
//			System.out.println("connected");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		try {
//			Utilisateur u = new Utilisateur();
//			u.setNoUtilisateur(1);
//			Categorie cat = new Categorie();
//			cat.setNoCategorie(1);
//			ArticleVendu art1 = new ArticleVendu("couteau", "Santoku alvéolé 24cm idééal légumes", LocalDate.now(), (LocalDate.now()), 5, 50, 0, u, cat);
//			System.out.println(art1);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
