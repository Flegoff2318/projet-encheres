<%@page import="bo.ArticleVendu"%>
<%@page import="java.time.LocalDate"%>
<%@page import="bll.ArticleVenduManager"%>
<%@page import="java.sql.Date"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
ArticleVendu article = (ArticleVendu) request.getAttribute("article");
Date date = Date.valueOf(article.getDateFinEncheres());
%>
<html>
<%@include file="fragments/head.jsp"%>
<body>
	<%@include file="fragments/header.jsp"%>
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
						<img src="${article.adresseImage != null ? article.adresseImage : 'https://placehold.co/300x300/green/white'}"
							alt="placeholder">
					</div>
					<div class="col col-lg-4">
						<table class="table table-hover">
							<thead>
								<tr class="table-primary">
									<th scope="col" colspan="2">${article.nomArticle}</th>
								</tr>
							</thead>
							<tbody>
								<tr class="table-active">
									<th scope="row">Description</th>
									<td>${article.description}</td>
								</tr>
								<tr class="table-dark">
									<th scope="row">Catégorie</th>
									<td>${article.categorie.libelle}</td>
								</tr>
								<tr class="table-active">
									<th scope="row">Meilleure offre</th>
									<td>${enchere>0?enchere:"aucune enchere"}</td>
								</tr>
								<tr class="table-dark">
									<th scope="row">Mise à prix</th>
									<td>${article.miseAPrix}</td>
								</tr>
								<tr class="table-active">
									<th scope="row">Fin de l'enchère</th>
									<td><fmt:formatDate value="<%=date%>" dateStyle="long"
											timeStyle="long" /></td>
								</tr>
								<tr class="table-dark">
									<th scope="row">Retrait</th>
									<td>
										<p>${retrait.rue}</p> <span>${retrait.ville}
											${retrait.code_postal}</span>
									</td>
								</tr>
							</tbody>
						</table>
						<div class="row mt-3 justify-content-md-center ">
							<c:if test="${utilisateur != article.utilisateur && article.etatVente==2}">
								<form action="" method="post">
									<div class="row justify-content-md-around mt-3 mb-3">
										<button type="submit" class="btn btn-primary">Confirmer
											le retrait</button>
										<a href="<%=request.getContextPath()%>"><button
												type="button" class="btn btn-danger ml-5">Annuler</button></a>
									</div>
								</form>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<%@include file="fragments/footer.jsp"%>
</body>
</html>
