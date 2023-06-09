<%@page import="java.time.LocalDate"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<%@include file="fragments/head.jsp"%>
<body>
	<%@include file="fragments/header.jsp"%>
	<main>
		<div class="container">
			<div class="row text-center">
				<div class="col mt-5 b-5">
					<h3>Nouvelle vente</h3>
				</div>
			</div>
			<div class="grid">
				<div class="row mt-3 justify-content-md-center">
					<div class="col col-lg-4 mt-5">
						<img alt="placeholder"
							src="https://placehold.co/300x300/green/white">
					</div>
					<form class="col col-lg-4" action="" method="post">
						<div class="row justify-content-md-left">
							<div class="form-group">
								<label class="col-form-label" for="nomArticle">Article :
								</label> <input type="text" class="form-control" name="nomArticle"
									id="nomArticle">
							</div>
						</div>
						<div class="row justify-content-md-left">
							<div class="form-group">
								<label class="col-form-label" for="description">Description
									: </label>
								<textarea class="form-control" name="description"
									id="description" rows="3" cols="20"></textarea>
							</div>
						</div>
						<div class="row justify-content-md-left">
							<div class="form-group">
								<label class="col-form-label" for="categorie">Categorie
									: </label> <select name ="categorie" id="categorie">
									<c:forEach var="c" items="${categories}">
										<option value="${c.noCategorie};${c.libelle}">${c.libelle}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row justify-content-md-left">
							<div class="form-group">
								<label class="" for="photo">Photo de
									l'article : </label>
									 <br>
									<input name="photo" id="photo" type="file">
							</div>
						</div>
						<div class="row justify-content-md-left">
							<div class="form-group">
								<label class="col-form-label mr-2" for="miseAPrix">Mise
									à prix : </label> <input type="number" name="miseAPrix" id="miseAPrix"
									min="1" value="1">
							</div>
						</div>
						<div class="row justify-content-md-left">
							<div class="form-group">
								<label class="col-form-label mr-4" for="miseAPrix">Début
									de l'enchere : </label> <input type="date" name="dateDebutEncheres"
									id="dateDebutEncheres" value="<%=LocalDate.now()%>"
									min="<%=LocalDate.now()%>">
							</div>
						</div>
						<div class="row justify-content-md-left">
							<div class="form-group">
								<label class="col-form-label mr-5" for="miseAPrix">Fin
									de l'enchere : </label> <input type="date" name="dateFinEncheres"
									id="dateFinEncheres" value="<%=LocalDate.now().plusDays(1)%>"
									min="<%=LocalDate.now().plusDays(1)%>">
							</div>
						</div>
						<div class="row justify-content-md-left">
							<fieldset class="border p-4 border-info">
								<legend class="text-center"> Retrait </legend>
								<div class="form-group d-flex justify-content-between">
									<label class="col-form-label" for="rue">Rue :</label> <input
										type="text" name="rue" id="rue" value="${utilisateur.rue}">
								</div>
								<div class="form-group d-flex justify-content-between">
									<label class="col-form-label mr-5" for="codePostal">Code
										postal :</label> <input type="text" name="codePostal" id="codePostal"
										value="${utilisateur.codePostal}">
								</div>
								<div class="form-group d-flex justify-content-between">
									<label class="col-form-label" for="ville">Ville :</label> <input
										type="text" name="ville" id="ville"
										value="${utilisateur.ville}">
								</div>
							</fieldset>
						</div>
						<div class="row justify-content-md-around mt-3 mb-3">
							<button type="submit" class="btn btn-primary">Enregistrer</button>
							<a href="<%=request.getContextPath()%>"><button
									type="button" class="btn btn-danger">Annuler</button></a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</main>
	<%@include file="fragments/footer.jsp"%>
</body>
</html>
