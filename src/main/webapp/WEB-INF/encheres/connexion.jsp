<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String message = (String) request.getAttribute("message");
%>
<html>
<%@include file="fragments/head.jsp"%>
<body>
<%@include file="fragments/header.jsp"%>
<main>
	<div class="container">
		<c:if test="${message!=null}">
			<div class="row mb-4 mt-5 justify-content-md-center">
				<div class="alert alert-dismissible alert-success">
					<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					<strong>${message}</strong>
					${sessionScope.utilisateur.noUtilisateur==requestScope.utilisateur.noUtilisateur}
				</div>
			</div>
		</c:if>
	<div class="container justify-content-md-center">
		<form method="post">
		
<!-- 		<div class="row mb-4"> -->
<!-- 			<input type="email" name="email" id="email" class="form-control" /> -->
<!-- 			<label class="form-label" for="email">Adresse e-mail</label> -->
<!-- 		</div> -->

		<div class="row mb-4">
			<input type="text" name="pseudo" id="pseudo" class="form-control" />
			<label class="form-label" for="pseudo">Pseudo</label>
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
		<button type="submit" class="btn btn-primary btn-block mb-4">Se connecter</button>
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
