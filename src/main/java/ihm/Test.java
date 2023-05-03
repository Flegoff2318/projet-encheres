package ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import bll.CategorieManager;
import bll.EnchereManager;
import bll.UtilisateurManager;
import bo.ArticleVendu;
import bo.Enchere;
import bo.Utilisateur;
import config.ConnectionProvider;

@WebServlet("/test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Utilisateur u = new Utilisateur();
//		u.setNoUtilisateur(4);
//		ArticleVendu a = new ArticleVendu();
//		a.setNoArticle(2);
//		Enchere enchere = new Enchere(LocalDateTime.now(), 800, u, a) ;		
		System.out.println(CategorieManager.getInstance().selectionnerCategories());
		System.out.println(CategorieManager.getInstance().selectionnerCategorieParId(2));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
