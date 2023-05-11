package ihm;

import bll.ArticleVenduManager;
import bo.ArticleVendu;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/encherir/*")
public class EncherirServlet extends HttpServlet {
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int idArticle = Integer.parseInt(request.getPathInfo().substring(1));
		 ArticleVendu articleVendu = ArticleVenduManager.getInstance().getArticleVendu(idArticle);
		 request.setAttribute("articleVendu",articleVendu);
		 request.setAttribute("enchereMin",120);
		 request.getRequestDispatcher("/WEB-INF/encheres/encherir.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doGet(request, response);
	}

}
