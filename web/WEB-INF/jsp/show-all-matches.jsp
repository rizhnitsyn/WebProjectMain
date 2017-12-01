<%--
  Created by IntelliJ IDEA.
  User: rizhnitsyn
  Date: 29.11.2017
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Matches of Tournament</title>
</head>
<body>
    ВЫВЕСТИ РЕЗУЛЬТАТЫ МАТЧЕЙ СЮДА В КРАСИВОЙ ТАБЛИЦЕ
    <form action="${pageContext.request.contextPath}/allMatches" method="post">
    <c:if test="${requestScope.tournamentState == 1}">
        <button type="submit" name="idTournament" value="${requestScope.tournamentId}">Добавить новый матч </button>
    </c:if>
    </form>
    <c:if test="${not empty requestScope.matches}">
        <c:forEach var="match" items="${requestScope.matches}">
            <p><a href="${pageContext.request.contextPath}/match?id=${match.id}">${match.firstTeam} - ${match.secondTeam}</a> ${match.matchDateTime}</p>
        </c:forEach>
    </c:if>

</body>
</html>
