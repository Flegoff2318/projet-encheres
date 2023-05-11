package filter;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import bo.Utilisateur;

@WebFilter (
		dispatcherTypes = {
				DispatcherType.REQUEST
				},
				urlPatterns =  {
						"/deconnexion",
						"/profil/*",
						"/nouvelle-vente"
				}				
		)

public class F2AuthFilter extends HttpFilter implements Filter {


	
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		
		//GUARD
		if (utilisateur == null) {
			//TODO a confirmer pour la redirection accueil ou login
			response.sendRedirect(request.getContextPath()+"");
			return;
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Starting ... filter Guard");
	}

}
