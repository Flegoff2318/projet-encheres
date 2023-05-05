<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<%@include file="fragments/head.jsp"%>
<body>
	<%@include file="fragments/header.jsp"%>
	<main>
		<div class="container justify-content-md-center">
			<div class="row text-center">
				<div class="col mt-5 b-5">
					<h3>Liste des enchères</h3>
				</div>
			</div>
			<form action="" method="post">
				<fieldset>
					<legend> Filtres : </legend>
					<div class="row g-2">
						<div class="col-6">
							<div class="p-3">
								<input class="form-control me-sm-2" type="search"
									placeholder="Le nom de l'article contient" name="recherche"> <label
									for="categorie" class="mt-2">Catégorie : </label> <select
									class="form-select" id="categorie" name="categorie">
									<option selected value="0">
											Toutes
										</option>
									<c:forEach var="categorie" items="${categories}">
										<option value="${categorie.noCategorie}">
											${categorie.libelle}
										</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-6">
							<div class="p-3">
								<button class="btn btn-lg btn-primary" type="submit">Rechercher</button>
							</div>
						</div>
					</div>
					<c:if test="${utilisateur!=null}">
						<!-- Champs de recherche utilisateur connecté -->


						<div class="row gy-2">
							<div class="col-3">
								<input type="radio" class="btn-check" name="type" id="achat"
									autocomplete="off" checked> <label
									class="btn btn-outline-primary" for="achat">Achats</label>

							</div>
							<div class="col-3">
								<input type="radio" class="btn-check" name="type" id="vente"
									autocomplete="off"> <label
									class="btn btn-outline-primary" for="vente">Mes ventes</label>
							</div>
						</div>

						<div class="row gy-2">
							<div class="col-3">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="encheres-ouvertes"> <label
										class="form-check-label" for="encheres-ouvertes">
										enchères ouvertes </label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="encheres-en-cours"> <label
										class="form-check-label" for="encheres-en-cours"> mes
										enchères en cours </label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="encheres-remportees"> <label
										class="form-check-label" for="encheres-remportees">
										mes enchères remportées </label>
								</div>
							</div>
							<div class="col-3">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="ventes-en-cours"> <label class="form-check-label"
										for="ventes-en-cours"> mes ventes en cours </label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="ventes-non-debutees"> <label
										class="form-check-label" for="ventes-non-debutees">
										ventes non débutées </label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="ventes-terminees"> <label class="form-check-label"
										for="ventes-terminees"> ventes terminées </label>
								</div>
							</div>
						</div>
					</c:if>
				</fieldset>
			</form>
			<div class="row gy-5">
				<c:forEach var="article" items="${articles}">
					<!--  if >2 alors on passe a la ligne -->
					<div class="col-6">
						<div>
							<img alt="placeholder"
								src="https://placehold.co/300x300/green/white">
						</div>
						<div class="p-3">
							<p>
								<a href="#">${article.nomArticle}</a>
							</p>
							<p>Prix : ${article.prixVente}</p>
							<p>Fin de l'enchère : ${article.dateFinEncheres}</p>
							<p> Vendeur : 
								<c:choose>
									<c:when test="${sessionScope.utilisateur != null}">
										<a href="<%= request.getContextPath() %>/profil/${article.utilisateur.noUtilisateur}">${article.utilisateur.pseudo}</a>
									</c:when>
									<c:otherwise>
										${article.utilisateur.pseudo}
									</c:otherwise>
								</c:choose>
							</p>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</main>
	<%@include file="fragments/footer.jsp"%>
</body>
</html>
