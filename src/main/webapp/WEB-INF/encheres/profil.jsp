<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<%@include file="fragments/head.jsp"%>
<body>
	<%@include file="fragments/header.jsp"%>
	<main>
		<div class="container">
			<div class="row justify-content-md-center mt-5">
				<div class="col col-lg-2">
					<p>Pseudo :</p>
				</div>
				<div class="col col-lg-2">
					<p><strong>le pseudo</strong></p>
				</div>
			</div>
			<div class="row justify-content-md-center">
				<div class="col col-lg-2">
					<p>Nom :</p>
				</div>
				<div class="col col-lg-2">
					<p>le nom</p>
				</div>
			</div>
			<div class="row justify-content-md-center">
				<div class="col col-lg-2">
					<p>Prénom :</p>
				</div>
				<div class="col col-lg-2">
					<p>le prénom</p>
				</div>
			</div>
			<div class="row justify-content-md-center">
				<div class="col col-lg-2">
					<p>Email :</p>
				</div>
				<div class="col col-lg-2">
					<p>l'adresse email</p>
				</div>
			</div>
			<div class="row justify-content-md-center">
				<div class="col col-lg-2">
					<p>Téléphone :</p>
				</div>
				<div class="col col-lg-2">
					<p>le numéro de téléphone</p>
				</div>
			</div>
			<div class="row justify-content-md-center">
				<div class="col col-lg-2">
					<p>Rue :</p>
				</div>
				<div class="col col-lg-2">
					<p>la rue</p>
				</div>
			</div>
			<div class="row justify-content-md-center">
				<div class="col col-lg-2">
					<p>Code postal :</p>
				</div>
				<div class="col col-lg-2">
					<p>le code postal</p>
				</div>
			</div>
			<div class="row justify-content-md-center">
				<div class="col col-lg-2">
					<p>Ville :</p>
				</div>
				<div class="col col-lg-2">
					<p>nom de la ville</p>
				</div>
			</div>
			<c:if test="${ utilisateur!=null }">
			<div class="row justify-content-md-center mt-5">
				<div class="col col-lg-2">
					<a class="btn btn-primary" href="<%=request.getContextPath()%>/profil/modifier/">Modifier</a>
				</div>
			</div>
			</c:if>
		</div>
	</main>
	<%@include file="fragments/footer.jsp"%>
</body>
</html>
