<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<%@include file="fragments/head.jsp"%>
<body>
	<%@include file="fragments/header.jsp"%>

	<main>
		<div class="d-flex">
			<div class="container justify-content-md-center">
				<div class="row text-center">
					<div class="col mt-5 b-5">
						<h3>Liste des enchères</h3>
					</div>
				</div>

				<div class="row gy-5 mt-5">
					<!-- 2eme bouton de gauche appuyé -->
					<c:forEach var="enchere" items="${encheresUtilisateur}">
						<c:if
							test="${enchere.articleVendu.etatVente==1 && encheresEnCours}">
							<!--  if >2 alors on passe a la ligne -->
							<div class="col-4">
								<div>
									<img alt="placeholder"
										src="https://placehold.co/300x300/green/white">
								</div>
								<div class="p-3 mb-5" style="width: 300px; background-color : rgb(102, 102, 153)">
									<p>En cours :
										<a class="text-light font-weight-bold"
											href="<%= request.getContextPath() %>/encherir/${enchere.articleVendu.noArticle}">${enchere.articleVendu.nomArticle}</a>
									</p>
									<p>Prix :
										${enchere.articleVendu.prixVente>0?enchere.articleVendu.prixVente:enchere.articleVendu.miseAPrix}</p>
									<p>Fin de l'enchère :
										${enchere.articleVendu.dateFinEncheres}</p>
									<p>
										Vendeur : <a class="text-light font-weight-bold"
											href="<%= request.getContextPath() %>/profil/${enchere.articleVendu.utilisateur.noUtilisateur}">${enchere.articleVendu.utilisateur.pseudo}</a>
									</p>
								</div>
							</div>
						</c:if>
					</c:forEach>
					<!-- 3eme bouton de gauche appuyé -->
					<c:forEach var="enchere" items="${encheresUtilisateur}">
						<c:if
							test="${enchere.articleVendu.etatVente>=2 && encheresRemportees
							&& enchere.montant_enchere == enchere.articleVendu.prixVente}">
							<!--  if >2 alors on passe a la ligne -->
							<div class="col-4">
								<div>
									<img alt="placeholder"
										src="https://placehold.co/300x300/green/white">
								</div>
								<div class="p-3 mb-5" style="width: 300px; background-color : rgb(38, 115, 77)">
									<p>${enchere.articleVendu.etatVente==2?"Remporté :":"Récupéré :"}
										<a class="text-light font-weight-bold"
											href="<%= request.getContextPath() %>/vente-terminee/${enchere.articleVendu.noArticle}">${enchere.articleVendu.nomArticle}</a>
									</p>
									<p>Prix :
										${enchere.articleVendu.prixVente>0?enchere.articleVendu.prixVente:enchere.articleVendu.miseAPrix}</p>
									<p>Fin de l'enchère :
										${enchere.articleVendu.dateFinEncheres}</p>
									<p>
										Vendeur : <a class="text-light font-weight-bold"
											href="<%= request.getContextPath() %>/profil/${enchere.articleVendu.utilisateur.noUtilisateur}">${enchere.articleVendu.utilisateur.pseudo}</a>
									</p>
								</div>
							</div>
						</c:if>
					</c:forEach>
					<!-- 1er bouton de gauche appuyé -->
					<c:forEach var="article" items="${articles}">
						<c:if test="${article.etatVente==1}">
							<!--  if >2 alors on passe a la ligne -->
							<div class="col-4">
								<div>
									<img alt="placeholder"
										src="https://placehold.co/300x300/green/white">
								</div>
								<div class="p-3 bg-dark mb-5" style="width: 300px;">
									<p>
										<a class="font-weight-bold"
											href="<%= request.getContextPath() %>/encherir/${article.noArticle}">${article.nomArticle}</a>
									</p>
									<p>Prix :
										${article.prixVente>0?article.prixVente:article.miseAPrix}</p>
									<p>Fin de l'enchère : ${article.dateFinEncheres}</p>
									<p>
										Vendeur :
										<c:choose>
											<c:when test="${sessionScope.utilisateur != null}">
												<a class="font-weight-bold"
													href="<%= request.getContextPath() %>/profil/${article.utilisateur.noUtilisateur}">${article.utilisateur.pseudo}</a>
											</c:when>
											<c:otherwise>
										${article.utilisateur.pseudo}
									</c:otherwise>
										</c:choose>
									</p>
								</div>
							</div>
						</c:if>
					</c:forEach>
					<!-- 1er bouton de droite appuyé -->
					<c:forEach var="article" items="${ventesUtilisateur}">
						<c:if test="${article.etatVente==1 && ventesEnCours}">
							<!--  if >2 alors on passe a la ligne -->
							<div class="col-4">
								<div>
									<img alt="placeholder"
										src="https://placehold.co/300x300/green/white">
								</div>
								<div class="p-3 mb-5" style="width: 300px; background-color : rgb(102, 102, 153)">
									<p>Vente en cours :
										<a class="text-light font-weight-bold"
											href="<%= request.getContextPath() %>/encherir/${article.noArticle}">${article.nomArticle}</a>
									</p>
									<p>Prix :
										${article.prixVente>0?article.prixVente:article.miseAPrix}</p>
									<p>Fin de l'enchère : ${article.dateFinEncheres}</p>
									<p>
										Vendeur : <a class="text-light font-weight-bold"
											href="<%= request.getContextPath() %>/profil/${article.utilisateur.noUtilisateur}">${article.utilisateur.pseudo}</a>
									</p>
								</div>
							</div>
						</c:if>
					</c:forEach>
					<!-- 2eme bouton de droite appuyé -->
					<c:forEach var="article" items="${ventesUtilisateur}">
						<c:if test="${article.etatVente==0 && ventesNonDebutees}">
							<!--  if >2 alors on passe a la ligne -->
							<div class="col-4">
								<div>
									<img alt="placeholder"
										src="https://placehold.co/300x300/green/white">
								</div>
								<div class="p-3 bg-secondary mb-5" style="width: 300px;">
									<p>Non débuté : 
										<a
											href="<%= request.getContextPath() %>/encherir/${article.noArticle}">${article.nomArticle}</a>
									</p>
									<p>Prix :
										${article.prixVente>0?article.prixVente:article.miseAPrix}</p>
									<p>Fin de l'enchère : ${article.dateFinEncheres}</p>
									<p>
										Vendeur : <a
											href="<%= request.getContextPath() %>/profil/${article.utilisateur.noUtilisateur}">${article.utilisateur.pseudo}</a>
									</p>
								</div>
							</div>
						</c:if>
					</c:forEach>
					<!-- 3eme bouton de droite appuyé -->
					<c:forEach var="article" items="${ventesUtilisateur}">
						<c:if test="${article.etatVente>=2 && ventesTerminees}">
							<!--  if >2 alors on passe a la ligne -->
							<div class="col-4">
								<div>
									<img alt="placeholder"
										src="https://placehold.co/300x300/green/white">
								</div>
								<div class="p-3 mb-5" style="width: 300px; background-color : rgb(38, 115, 77)">
									<p>${article.etatVente==2?"Vente terminée :":"Article récupéré :"}
										<a class="text-light font-weight-bold"
											href="<%= request.getContextPath() %>/encherir/${article.noArticle}">${article.nomArticle}</a>
									</p>
									<p>Prix :
										${article.prixVente>0?article.prixVente:article.miseAPrix}</p>
									<p>Fin de l'enchère : ${article.dateFinEncheres}</p>
									<p>
										Vendeur : <a class="text-light font-weight-bold"
											href="<%= request.getContextPath() %>/profil/${article.utilisateur.noUtilisateur}">${article.utilisateur.pseudo}</a>
									</p>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
			<div class="col-3">
				<aside class="mt-5 pt-5 sticky-top">
					<form action="" method="post">
						<fieldset>
							<legend> Filtres : </legend>
							<div class="row g-2">
								<div class="col-6">
									<div class="p-3">
										<input class="form-control me-sm-2" type="search"
											placeholder="Nom de l'article..." name="recherche"
											value="${rechercheChoisie}"> <label for="categorie"
											class="mt-2">Catégorie : </label> <select class="form-select"
											id="categorie" name="categorie">
											<option selected value="0">Toutes</option>
											<c:forEach var="categorie" items="${categories}">
												<option
													${categorieChoisie==categorie.noCategorie?"selected":""}
													value="${categorie.noCategorie}">
													${categorie.libelle}</option>
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
									<div class="col-6 auctions">
										<input type="radio" class="btn-check" name="type"
											${type!="sales"?"checked":""} id="auctions"
											onchange="change(event);" value="auctions"> <label
											class="btn btn-outline-primary" for="auctions">Achats</label>

										<div class="form-check">
											<input class="form-check-input" type="checkbox" value="check"
												${encheresOuvertes?"checked":""} name="encheres-ouvertes"
												${type=="sales"?"disabled":""} id="encheres-ouvertes"> <label
												class="form-check-label" for="encheres-ouvertes">
												enchères ouvertes </label>
										</div>
										<div class="form-check">
											<input class="form-check-input" type="checkbox" value="check"
												${encheresEnCours?"checked":""} name="encheres-en-cours"
												${type=="sales"?"disabled":""} id="encheres-en-cours"> <label
												class="form-check-label" for="encheres-en-cours">
												mes enchères en cours </label>
										</div>
										<div class="form-check">
											<input class="form-check-input" type="checkbox" value="check"
												${encheresRemportees?"checked":""} name="encheres-remportees" 
												${type=="sales"?"disabled":""} id="encheres-remportees">
											<label class="form-check-label" for="encheres-remportees">
												mes enchères remportées </label>
										</div>

									</div>
									<div class="col-6 sales">
										<input type="radio" class="btn-check" name="type"
											${type=="sales"?"checked":""} id="sales" onchange="change(event);"
											value="sales"> <label class="btn btn-outline-primary"
											for="sales">Mes ventes</label>

										<div class="form-check">
											<input class="form-check-input" type="checkbox" value="check"
												${ventesEnCours?"checked":""} name="ventes-en-cours"
												${type!="sales"?"disabled":""} id="ventes-en-cours"> <label
												class="form-check-label" for="ventes-en-cours"> mes
												ventes en cours </label>
										</div>
										<div class="form-check">
											<input class="form-check-input" type="checkbox" value="check"
												${ventesNonDebutees?"checked":""} name="ventes-non-debutees"
												${type!="sales"?"disabled":""} id="ventes-non-debutees"> <label
												class="form-check-label" for="ventes-non-debutees">
												ventes non débutées </label>
										</div>
										<div class="form-check">
											<input class="form-check-input" type="checkbox" value="check"
												${ventesTerminees?"checked":""} name="ventes-terminees"
												${type!="sales"?"disabled":""} id="ventes-terminees"> <label
												class="form-check-label" for="ventes-terminees">
												ventes terminées </label>
										</div>

									</div>
								</div>

							</c:if>
						</fieldset>
					</form>
				</aside>
			</div>
		</div>
	</main>

	<%@include file="fragments/footer.jsp"%>
	<script>
    function change(e){
        if(e.target.value==="sales"){ 
            disable(".auctions input[type=checkbox]");
        }else{
            disable(".sales input[type=checkbox]");
        }
        var parent = e.target.parentNode;
        enable(parent);
    }
    
    function disable(selector){
        var listeChecks = document.querySelectorAll(selector);
            listeChecks.forEach((value,index,array)=>{
                value.disabled= true;
                value.checked= false;
        });     
    }
    
    function enable(node){
        var listeChecks = node.querySelectorAll("input[type=checkbox]");
        listeChecks.forEach((value,index,array)=>{
            value.disabled= false;          
        }); 
    }
</script>
</body>
</html>
