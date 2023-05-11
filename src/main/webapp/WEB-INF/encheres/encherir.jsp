<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="fragments/head.jsp" %>
<body>
<%@include file="fragments/header.jsp" %>
<main>
<div class="container">
    <div class="row text-center">
        <div class="col mt-5 mb-5">
            <h3>Détail vente</h3>
        </div>
    </div>
    <div class="grid">
        <div class="row mt-5 justify-content-md-center">
            <div class="col col-lg-4">
                <img src="https://placehold.co/300x300/green/white" alt="placeholder">
            </div>
            <div class="col col-lg-4">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col" colspan="2">${articleVendu.nomArticle}</th>
                        
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="table-active">
                        <th scope="row">Description</th>
                        <td>
                           ${articleVendu.description}
                        </td>
                    </tr>
                    <tr class="table-light">
                        <th scope="row">Catégorie</th>
                        <td>${articleVendu.categorie}</td>
                    </tr>
                    <tr class="table-active">
                        <th scope="row">Meilleure offre</th>
                        <td>nombre de crédits de la meilleure offre</td>
                    </tr>
                    <tr class="table-light">
                        <th scope="row">Mise à prix</th>
                        <td>${articleVendu.miseAPrix}</td>
                    </tr>
                       <tr class="table-active">
                        <th scope="row">Début de l'enchère</th>
                        <td>${articleVendu.dateDebutEncheres}</td>
                    </tr>
                    <tr class="table-light">
                        <th scope="row">Fin de l'enchère</th>
                        <td>${articleVendu.dateFinEncheres}</td>
                    </tr>
                    <tr class="table-active">
                        <th scope="row">Vendeur</th>
                        <td>${articleVendu.utilisateur}</td>
                    </tr>
                     <tr class="table-light">
                        <th scope="row">Retrait</th>
                        <td>${articleVendu.utilisateur.rue}</td>
                    </tr>
                    </tbody>
                </table>
                <div class="row mt-5 justify-content-md-center">
                    <label class="ml-3" for="proposition">Ma proposition : </label>
                    <input class="ml-3" type="number" id="proposition" name="proposition" min="${enchereMin}" value="${enchereMin}">
                    <button>Enchérir</button>

                </div>
            </div>
        </div>
    </div>
</div>
</main>
<%@include file="fragments/footer.jsp" %>
</body>
</html>
