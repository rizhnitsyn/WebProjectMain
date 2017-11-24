<%--
  Created by IntelliJ IDEA.
  User: rizhnitsyn
  Date: 22.11.2017
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>TournamentList Forecasts</title>
</head>
<body>
    <p>Выберите турнир для просмотра календаря турнира и результатов матча:</p>
    <c:forEach var="tournament" items="${requestScope.tournaments}">
        <p><a href="${pageContext.request.contextPath}/allMatches?id=${tournament.id}">${tournament.name}</a>  Начало: ${tournament.startDate} Состояние: ${tournament.state}</p>
    </c:forEach>
</body>
</html>
