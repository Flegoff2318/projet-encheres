<%@page import="java.time.LocalDate"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="fragments/head.jsp" %>
<body>
<%@include file="fragments/header.jsp" %>
<main>
	<div class="container">
		<div class="row text-center">
        	<div class="col mt-5 b-5">
            	<h3>Nouvelle vente</h3>
        	</div>
    	</div>
    	<div>
			<div class="col col-lg-3">
			<img alt="placeholder"
				src="https://placehold.co/300x300/green/white">
			</div>
			<form action="" method="post">
				<fieldset>
	            	<div class="row justify-content-md-center">
	                	<div class="col col-lg-3">
	                    	<div class="form-group">
	                        	<label class="col-form-label mt-4" for="nomArticle">Article : </label> <input
	                            	type="text" class="form-control" name="nomArticle"
	                            	id="nomArticle">
	                    	</div>
	                	</div>
					</div>
	            	<div class="row justify-content-md-center">
	                	<div class="col col-lg-3">
	                    	<div class="form-group">
	                        	<label class="col-form-label mt-4" for="description">Description : </label> 
	                        	<textarea class="form-control" name="description" id="description" rows="4" cols="30"></textarea>
	                    	</div>
	                	</div>
					</div>
	            	<div class="row justify-content-md-center">
	                	<div class="col col-lg-3">
	                    	<div class="form-group">
	                        	<label class="col-form-label mt-4" for="categorie">Categorie : </label> 
	                        	<select>
	                        		<option>1</option>
	                        		<option>2</option>
	                        		<option>3</option>
	                        		<option>4</option>
	                        	</select>
	                    	</div>
	                	</div>
					</div>
					<div class="row justify-content-md-center">
	                	<div class="col col-lg-3">
	                    	<div class="form-group">
	                        	<label class="col-form-label mt-4" for="photo">Photo de l'article : </label> 
	                        	<input name="photo" id="photo" type="file">
	                    	</div>
	                	</div>
					</div>
					<div class="row justify-content-md-center">
	                	<div class="col col-lg-3">
	                    	<div class="form-group">
	                        	<label class="col-form-label mt-4" for="miseAPrix">Mise à prix : </label> 
	                        	<input type="number" name="miseAPrix" id="miseAPrix" min="1">
	                    	</div>
	                	</div>
					</div>
					<div class="row justify-content-md-center">
	                	<div class="col col-lg-3">
	                    	<div class="form-group">
	                        	<label class="col-form-label mt-4" for="miseAPrix">Début de l'enchere : </label> 
	                        	<input type="date" name="dateDebutEncheres" id="dateDebutEncheres" value="<%= LocalDate.now() %>" min="<%= LocalDate.now() %>">
	                    	</div>
	                	</div>
					</div>
					<div class="row justify-content-md-center">
	                	<div class="col col-lg-3">
	                    	<div class="form-group">
	                        	<label class="col-form-label mt-4" for="miseAPrix">Fin de l'enchere : </label> 
	                        	<input type="date" name="dateFinEncheres" id="dateFinEncheres" value="<%= LocalDate.now().plusDays(1) %>" min="<%= LocalDate.now().plusDays(1) %>">
	                    	</div>
	                	</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</main>
<%@include file="fragments/footer.jsp" %>
</body>
</html>
