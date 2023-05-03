<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<%@include file="fragments/head.jsp"%>
<body>
<%@include file="fragments/header.jsp"%>
<main>
	<div class="container">
		<form>
			<div class="row mb-4 mt-5 justify-content-md-center">
				<div class="col col-lg-6">
					<label class="form-label" for="email">Adresse e-mail</label>
					<input type="email" name="email" id="email" class="form-control" />
				</div>
			</div>
			<div class="row mb-4 justify-content-md-center">
				<div class="col col-lg-6">
					<label class="form-label" for="motdepasse">Password</label>
					<input type="password" name="motdepasse" id="motdepasse" class="form-control" />
				</div>
			</div>
			<div class="row mb-4 justify-content-md-center">
				<!-- Checkbox -->
				<div class="col col-lg-2">
					<input class="form-check-input" type="checkbox" value="souvenir" id="souvenir" />
					<label class="form-check-label" for="souvenir">Se souvenir de moi </label>
				</div>
				<div class="col col-lg-2">
					<!-- Simple link -->
					<a href="#">Mot de passe oublié</a>
				</div>
			</div>
			<div class="row mb-4 justify-content-md-center">
				<div class="col col-lg-6">
					<button type="button" class="btn btn-primary btn-block mb-4">Se connecter</button>
				</div>
			</div>
			<div class="row mb-4 justify-content-md-center">
				<div class="col col-lg-2">
					<a href="<%=request.getContextPath()%>/creer-compte">Créer un compte</a>
				</div>
			</div>
		</form>
	</div>
</main>
<%@include file="fragments/footer.jsp"%>
</body>
</html>
