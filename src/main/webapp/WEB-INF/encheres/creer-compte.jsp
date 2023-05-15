<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<%@include file="fragments/head.jsp"%>
<body>
	<%@include file="fragments/header.jsp"%>
	<main>
		<div class="container">
			<div class="row text-center">
				<div class="col mt-5 b-5">
					<h3>Création de compte</h3>
				</div>
			</div>
			<c:if test="${erreurs!=null}">
				<div class="row mb-4 mt-5 justify-content-md-center">
					<div class="alert alert-dismissible alert-danger">
						<strong>${erreurs}</strong>
					</div>
				</div>
			</c:if>
			<c:if test="${match!=null}">
				<div class="row mb-4 mt-5 justify-content-md-center">
					<div class="alert alert-dismissible alert-danger">
						<strong>${match}</strong>
					</div>
				</div>
			</c:if>
			<form action="" method="post">
				<fieldset>
					<div class="row justify-content-md-center">
						<div class="col col-lg-3">
							<div class="form-group">
								<label class="col-form-label mt-4" for="pseudo">Pseudo</label>
								<input type="text" class="form-control" name="pseudo"
									placeholder="Pseudo" id="pseudo" value="${not empty utilisateur ? utilisateur.pseudo : ''}" >
							</div>
						</div>
						<div class="col col-lg-3">
							<div class="form-group">
								<label class="col-form-label mt-4" for="nom">Nom</label> <input
									type="text" class="form-control" name="nom" placeholder="Nom"
									id="nom" value="${not empty utilisateur ? utilisateur.nom : ''}" >
							</div>
						</div>
					</div>
					<div class="row justify-content-md-center">
						<div class="col col-lg-3">
							<div class="form-group">
								<label class="col-form-label mt-4" for="prenom">Prénom</label> <input
									type="text" class="form-control" name="prenom"
									placeholder="Prénom" id="prenom" value="${not empty utilisateur ? utilisateur.prenom : ''}" >
							</div>
						</div>
						<div class="col col-lg-3">
							<div class="form-group">
								<label class="col-form-label mt-4" for="email">Email</label> <input
									type="email" class="form-control" name="email"
									placeholder="Email" id="email" value="${not empty utilisateur ? utilisateur.email : ''}" >
							</div>
						</div>
					</div>
					<div class="row justify-content-md-center">
						<div class="col col-lg-3">
							<div class="form-group">
								<label class="col-form-label mt-4" for="telephone">Téléphone</label>
								<input type="text" class="form-control" name="telephone"
									placeholder="Téléphone" id="telephone" value="${not empty utilisateur ? utilisateur.telephone : ''}" >
							</div>
						</div>
						<div class="col col-lg-3">
							<div class="form-group">
								<label class="col-form-label mt-4" for="rue">Rue</label> <input
									type="text" class="form-control" name="rue" placeholder="Rue"
									id="rue" value="${not empty utilisateur ? utilisateur.rue : ''}" >
							</div>
						</div>
					</div>
					<div class="row justify-content-md-center">
						<div class="col col-lg-3">
							<div class="form-group">
								<label class="col-form-label mt-4" for="codepostal">Code
									postal</label> <input type="text" class="form-control"
									name="codepostal" placeholder="Code postal" id="codepostal" value="${not empty utilisateur ? utilisateur.codePostal : ''}" >
							</div>
						</div>
						<div class="col col-lg-3">
							<div class="form-group">
								<label class="col-form-label mt-4" for="ville">Ville</label> <input
									type="text" class="form-control" name="ville"
									placeholder="Ville" id="ville" value="${not empty utilisateur ? utilisateur.ville : ''}" >
							</div>
						</div>
					</div>
					<div class="row justify-content-md-center">
						<div class="col col-lg-3">
							<div class="form-group">
								<label class="col-form-label mt-4" for="motdepasse">Mot
									de passe</label> <input type="password" class="form-control"
									name="motdepasse" id="motdepasse">
							</div>
						</div>
						<div class="col col-lg-3">
							<div class="form-group">
								<label class="col-form-label mt-4" for="confirmation">Confirmation</label>
								<input type="password" class="form-control" name="confirmation"
									id="confirmation">
							</div>
						</div>
					</div>

					<div class="row justify-content-md-center">
						<div class="col col-lg-2">
							<input class="btn btn-primary" type="submit" value="Créer">
						</div>
						<div class="col col-lg-2">
							<a class="btn btn-danger" href="<%=request.getContextPath()%>/">
								Annuler </a>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</main>
	<%@include file="fragments/footer.jsp"%>
</body>
</html>

</main>
<%@include file="fragments/footer.jsp"%>
</body>
</html>
