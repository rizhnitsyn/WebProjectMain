
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>TournamentList Forecasts</title>
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="header.jsp"%>

    <h2 class="form-title">Выберите турнир для просмотра итогов конкурса прогнозов:</h2>
    <ul class="widget-list2">
        <c:forEach var="tournament" items="${requestScope.tournaments}">
            <li><a href="${pageContext.request.contextPath}/resultTable?id=${tournament.id}">${tournament.name}</a><p> Старт турнира: ${tournament.startDate}, ${tournament.state}</p></li>
        </c:forEach>
    </ul>

<%@include file="footer.jsp"%>
</body>
</html>
