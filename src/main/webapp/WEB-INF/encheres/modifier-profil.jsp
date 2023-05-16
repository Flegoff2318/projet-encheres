<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="fragments/head.jsp" %>
<body>
<%@include file="fragments/header.jsp" %>
<main>
<div class="container">
    <div class="row text-center">
        <div class="col mt-5 b-5">
            <h3>Mon profil</h3>
        </div>
    </div>
    <c:if test="${erreur!=null}">
        <div class="row mb-4 mt-5 justify-content-md-center">
            <div class="alert alert-dismissible alert-success">
                <strong>${erreur}</strong>       
            </div>
        </div>
    </c:if>
    <c:if test="${erreurs!=null}">
        <div class="row mb-4 mt-5 justify-content-md-center">
            <div class="alert alert-dismissible alert-success">
                <strong>${erreurs}</strong>
            </div>
        </div>
    </c:if>
    <form action="" method="post">
        <fieldset>
            <div class="row justify-content-md-center">
                <div class="col col-lg-3">
                    <div class="form-group">
                        <label class="col-form-label mt-4" for="pseudo">Pseudo</label> <input
                            type="text" class="form-control" name="pseudo"
                            value="${utilisateur.pseudo}" id="pseudo">
                    </div>
                </div>
                <div class="col col-lg-3">
                    <div class="form-group">
                        <label class="col-form-label mt-4" for="nom">Nom</label> <input
                            type="text" class="form-control" name="nom" value="${utilisateur.nom}"
                            id="nom">
                    </div>
                </div>
            </div>
            <div class="row justify-content-md-center">
                <div class="col col-lg-3">
                    <div class="form-group">
                        <label class="col-form-label mt-4" for="prenom">Prénom</label> <input
                            type="text" class="form-control" name="prenom"
                            value="${utilisateur.prenom}" id="prenom">
                    </div>
                </div>
                <div class="col col-lg-3">
                    <div class="form-group">
                        <label class="col-form-label mt-4" for="email">Email</label> <input
                            type="email" class="form-control" name="email"
                            value="${utilisateur.email}" id="email">
                    </div>
                </div>
            </div>
            <div class="row justify-content-md-center">
                <div class="col col-lg-3">
                    <div class="form-group">
                        <label class="col-form-label mt-4" for="telephone">Téléphone</label>
                        <input type="text" class="form-control" name="telephone"
                               value="${utilisateur.telephone}" id="telephone">
                    </div>
                </div>
                <div class="col col-lg-3">
                    <div class="form-group">
                        <label class="col-form-label mt-4" for="rue">Rue</label> <input
                            type="text" class="form-control" name="rue" value="${utilisateur.rue}"
                            id="rue">
                    </div>
                </div>
            </div>
            <div class="row justify-content-md-center">
                <div class="col col-lg-3">
                    <div class="form-group">
                        <label class="col-form-label mt-4" for="codepostal">Code
                            postal</label> <input type="text" class="form-control"
                                                  name="codepostal" value="${utilisateur.codePostal}" id="codepostal">
                    </div>
                </div>
                <div class="col col-lg-3">
                    <div class="form-group">
                        <label class="col-form-label mt-4" for="ville">Ville</label> <input
                            type="text" class="form-control" name="ville"
                            value="${utilisateur.ville}" id="ville">
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
                <span>Crédits : ${utilisateur.credit}</span>
            </div>

            <div class="row justify-content-md-center">
                <div class="col col-lg-2">
                    <input class="btn btn-primary btn-lg" type="submit" value="Enregistrer">
                </div>
                <div class="col col-lg-2">
                    <a class="btn btn-danger btn-lg" onclick="return confirm('Etes vous sur de vouloir supprimer votre compte ?')" href="<%=request.getContextPath()%>/profil/supprimer">
                        Supprimer mon compte
                    </a>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</main>
<%@include file="fragments/footer.jsp" %>
</body>
</html>
