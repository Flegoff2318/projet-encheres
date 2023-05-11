package filter;

import bll.UtilisateurManager;
import bo.Token;
import bo.Utilisateur;
import helpers.TokenGenerator;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter (
		dispatcherTypes = {
				DispatcherType.REQUEST
		},
		urlPatterns =  {
				"/*"
		}
		
)

public class F1RememberMeFilter extends HttpFilter implements Filter {



	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = request.getSession();
		if(session.getAttribute("utilisateur")==null){
			// récupérer les token depuis le navigateur
			Cookie userToken= getCookie("userToken",request.getCookies());
			Cookie passToken= getCookie("passToken",request.getCookies());
			if(userToken!=null && passToken!=null){
				//TODO : TRY CATCH
				Utilisateur utilisateur = UtilisateurManager.getInstance().selectionnerParToken(new Token(userToken.getValue(),passToken.getValue()));
				if(utilisateur!=null){
					session.setAttribute("utilisateur",utilisateur);
					// régénérer un autre token idéalement
				}
			}
		}
		chain.doFilter(request, response);
	}

	private Cookie getCookie(String key, Cookie[] cookies){
		if(cookies!=null) for (Cookie cookie:cookies
		) {
			if (cookie.getName().equals(key)){
				return  cookie;
			}
		}
		return null;
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Starting ... remember me");
	}

}
