package ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

import bll.ArticleVenduManager;
import bll.BLLException;
import bll.CategorieManager;
import bll.RetraitManager;
import bo.ArticleVendu;
import bo.Categorie;
import bo.Retrait;
import bo.Utilisateur;
import jakarta.servlet.http.Part;

@WebServlet("/nouvelle-vente")
public class NouvelleVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Categorie> categories = CategorieManager.getInstance().selectionnerCategories();
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/WEB-INF/encheres/nouvelle-enchere.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nomArticle = request.getParameter("nomArticle");
		String description = request.getParameter("description");
		int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
		LocalDate dateDebutEncheres = LocalDate.parse(request.getParameter("dateDebutEncheres"));
		LocalDate dateFinEncheres = LocalDate.parse(request.getParameter("dateFinEncheres"));
		//Image
		String adresseImage = stockerImage(request);
		//utilisateur
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		//categorie
		int noCategorie = Integer.parseInt(request.getParameter("categorie").split(";")[0]);
		String libelle = request.getParameter("categorie").split(";")[1];
		Categorie categorie = new Categorie(noCategorie, libelle);

		//retrait
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		
		ArticleVendu article = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, miseAPrix, 0, utilisateur, categorie);
		article.setAdresseImage(adresseImage); // TODO modifier le constructeur pour éviter cette étape (optionnel)
		Retrait retrait = new Retrait(article, rue, codePostal, ville);
		
		try {
			ArticleVenduManager.getInstance().addArticle(article);
			RetraitManager.getInstance().ajouterRetrait(retrait);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"");
	}

	public String stockerImage(HttpServletRequest request) throws ServletException, IOException {
		return null;
	}



}
