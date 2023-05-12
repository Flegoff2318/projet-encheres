package ihm;

import bll.TokenManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

import bo.Utilisateur;

@WebServlet("/deconnexion")
public class DeconnexionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        supprimerCookies(utilisateur,request,response);
        session.invalidate();
        response.sendRedirect(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    public void supprimerCookies(Utilisateur utilisateur,HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies
             ) {
            if(cookie.getName().equals("userToken") || cookie.getName().equals("passToken")){
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        TokenManager.getInstance().supprimerToken(utilisateur.getNoUtilisateur());
    }
}
