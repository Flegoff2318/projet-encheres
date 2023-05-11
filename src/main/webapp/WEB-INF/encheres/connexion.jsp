<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String message = (String) request.getAttribute("message");
%>
<html>
<%@include file="fragments/head.jsp"%>
<body>
	<%@include file="fragments/header.jsp"%>
	<main>
		
		<c:if test="${message!=null}">
			<div class="row mb-4 mt-5 justify-content-md-center">
				<div class="alert alert-dismissible alert-success">
					<!-- 					<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button> -->
					<strong>${message}</strong>
					<%-- 					${sessionScope.utilisateur.noUtilisateur==requestScope.utilisateur.noUtilisateur} --%>
				</div>
			</div>
		</c:if>
		<c:if test="${error!=null}">
			<div class="row mb-4 mt-5 justify-content-md-center">
				<div class="alert alert-dismissible alert-danger">
					<!-- 					<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button> -->
					<strong>${error}</strong>
					<%-- 					${sessionScope.utilisateur.noUtilisateur==requestScope.utilisateur.noUtilisateur} --%>
				</div>
			</div>
		</c:if>
		
		<div class="row text-center">
			<div class="col mt-5 b-5 mb-5">
				<h1>Connexion</h1>
			</div>
		</div>
		
		<div class="container justify-content-md-center mt-5">
			<form method="post" class="form-horizontal">
			
				<div class="col-6 offset-3">	
									
						<!-- 	Champs login pwd				 -->
						<div class="row g-3 align-items-center">
						  <div class="col-4">
						    <label for="pseudo" class="col-form-label">Identifiant :</label>
						  </div>
						  <div class="col-8">
						    <input type="text" id="pseudo" name="pseudo" class="form-control" aria-labelledby="">
						  </div>
						</div>							
	
						<div class="row g-3 align-items-center mt-3">
						  <div class="col-4">
						    <label for="password" class="col-form-label">Mot de passe :</label>
						  </div>
						  <div class="col-8">
						    <input type="password" id="password" name="password" class="form-control" aria-labelledby="passwordHelpInline">
						  </div>
						</div>	
						
						
						<!-- 	Btn cnx				 -->
						<div class="row g-3 mt-5">
							<div class="col-5 offset-1">
								<button type="submit" class="btn btn-lg btn-primary mb-4">Se connecter</button>								
							</div>
						
						<!-- 	Checkbox + link				 -->
						
							<div class="col-5 offset-1">	
							
								<!-- Checkbox -->
								<div class="form-check">
									<input class="form-check-input" type="checkbox"
										value="souvenir" id="souvenir" /> <label
										class="form-check-label" for="souvenir">Se souvenir de moi </label>
								</div>				
								
								<!-- Simple link -->
								<a href="#">Mot de passe oublié</a>
							</div>
						</div>	
					
				</div>				
				
			</form>
			
			<!-- 		btn creer compte			 -->						
			<div class="col-6 offset-3">
				<div class="d-grid gap-2 mt-5">
					<div >
						<button type="button" class="btn btn-block btn-primary" onclick="window.location.href='<%=request.getContextPath()%>/creer-compte'">Créer un
							compte</button>
					</div>
				</div>
			</div>	
		</div>
	</main>
	<%@include file="fragments/footer.jsp"%>
</body>
</html>
