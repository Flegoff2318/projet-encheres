<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<%@include file="fragments/head.jsp"%>
<body>
<%@include file="fragments/header.jsp"%>
<main>
	<div class="container justify-content-md-center">
		<form>
		<div class="row mb-4">
			<input type="email" name="email" id="email" class="form-control" />
			<label class="form-label" for="email">Adresse e-mail</label>
		</div>
		<div class="row mb-4">
			<input type="password" name="motdepasse" id="motdepasse" class="form-control" />
			<label class="form-label" for="motdepasse">Password</label>
		</div>
		<div class="col d-flex justify-content-center">
		<!-- Checkbox -->
		<div class="form-check">
			<input class="form-check-input" type="checkbox" value="souvenir" id="souvenir" />
			<label class="form-check-label" for="souvenir">Se souvenir de moi </label>
		</div>
			<div class="col">
				<!-- Simple link -->
				<a href="#">Mot de passe oublié</a>
			</div>
		</div>
		<button type="button" class="btn btn-primary btn-block mb-4">Se connecter</button>
		<div class="text-center">
			<p>
				<a href="<%=request.getContextPath()%>/creer-compte">Créer un compte</a>
			</p>
		</div>
		</form>
	</div>
</main>
<%@include file="fragments/footer.jsp"%>
</body>
</html>
