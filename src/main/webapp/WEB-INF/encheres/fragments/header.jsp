<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <a class="navbar-brand" href="<%=request.getContextPath()%>">ENI - Enchères</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <div class="ml-auto">
                <c:choose>
                    <c:when test="${utilisateur==null}">
                        <a href="<%=request.getContextPath()%>/connexion" class="ml-2 text-white">S'inscrire - Se connecter</a>
                    </c:when>
                    <c:otherwise>
                        <a href="<%=request.getContextPath()%>" class="ml-2 text-white">Enchères</a>
                        <a href="<%=request.getContextPath()%>/nouvelle-vente" class="ml-2 text-white">Vendre un article</a>
                        <a href="<%=request.getContextPath()%>/profil/${sessionScope.utilisateur.noUtilisateur}" class="ml-2 text-white">Mon profil</a>
                        <a href="<%=request.getContextPath()%>/deconnexion" class="ml-2 text-white">Déconnexion</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </nav>
</header>