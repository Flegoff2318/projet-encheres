<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="fragments/head.jsp" %>
<body>
<%@include file="fragments/header.jsp" %>
<main>
    <c:choose>
        <c:when test="${utilisateur==null}">
            <!-- TITRE -->
            <!-- FILTRES 4 CATEG -->
            <!-- 2 ARTICLES (cartes ?) -->
        </c:when>
        <c:otherwise>
            <!-- TITRE -->
            <!-- FILTRES 4 CATEG -->
            <!-- 2 ARTICLES (cartes ?) -->
        </c:otherwise>
    </c:choose>
</main>
<%@include file="fragments/footer.jsp" %>
</body>
</html>
