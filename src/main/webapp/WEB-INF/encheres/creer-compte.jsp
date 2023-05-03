<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<%@include file="fragments/head.jsp"%>
<body>
	<%@include file="fragments/header.jsp"%>
	<main>
		<div class="container">
			<div class="row text-center">
				<div class="col mt-5 b-5">
					<h3>Mon profil</h3>
				</div>
			</div>
			<form action="" method="post">
				<fieldset>
					<div class="row justify-content-md-center">
						<div class="col col-lg-3">
							<div class="form-group">
								<label class="col-form-label mt-4" for="pseudo">Pseudo</label> <input
									type="text" class="form-control" name="pseudo"
									placeholder="Pseudo" id="pseudo">
							</div>
						</div>
						<div class="col col-lg-3">
							<div class="form-group">
								<label class="col-form-label mt-4" for="nom">Nom</label> <input
									type="text" class="form-control" name="nom" placeholder="Nom"
									id="nom">
							</div>
						</div>
					</div>
					<div class="row justify-content-md-center">
						<div class="col col-lg-3">
							<div class="form-group">
								<label class="col-form-label mt-4" for="prenom">Prénom</label> <input
									type="text" class="form-control" name="prenom"
									placeholder="Prénom" id="prenom">
							</div>
						</div>
						<div class="col col-lg-3">
							<div class="form-group">
								<label class="col-form-label mt-4" for="email">Email</label> <input
									type="email" class="form-control" name="email"
									placeholder="Email" id="email">
							</div>
						</div>
					</div>
					<div class="row justify-content-md-center">
						<div class="col col-lg-3">
							<div class="form-group">
								<label class="col-form-label mt-4" for="telephone">Téléphone</label>
								<input type="text" class="form-control" name="telephone"
									placeholder="Téléphone" id="telephone">
							</div>
						</div>
						<div class="col col-lg-3">
							<div class="form-group">
								<label class="col-form-label mt-4" for="rue">Rue</label> <input
									type="text" class="form-control" name="rue" placeholder="Rue"
									id="rue">
							</div>
						</div>
					</div>
					<div class="row justify-content-md-center">
						<div class="col col-lg-3">
							<div class="form-group">
								<label class="col-form-label mt-4" for="codepostal">Code
									postal</label> <input type="text" class="form-control"
									name="codepostal" placeholder="Code postal" id="codepostal">
							</div>
						</div>
						<div class="col col-lg-3">
							<div class="form-group">
								<label class="col-form-label mt-4" for="ville">Ville</label> <input
									type="text" class="form-control" name="ville"
									placeholder="Ville" id="ville">
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
