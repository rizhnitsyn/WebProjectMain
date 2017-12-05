
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Matches for Forecast</title>
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="header.jsp"%>

    <c:if test="${not empty requestScope.matches}">
        <h3 class="form-title">Выберите матч для прогноза</h3>
        <div class="widget-list4">
        <c:forEach var="match" items="${requestScope.matches}">
            <p><a href="${pageContext.request.contextPath}/match?id=${match.id}">${match.firstTeam} - ${match.secondTeam}</a> <span class="span-class-list"> ${match.strMatchDateTime}</span></p>
        </c:forEach>
        </div>
    </c:if>

    <c:if test="${empty requestScope.matches}">
        <h3 class="form-title">Нет матчей для прогноза</h3>
    </c:if>
<%@include file="footer.jsp"%>
</body>
</html>
