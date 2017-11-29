<%--
  Created by IntelliJ IDEA.
  User: rizhnitsyn
  Date: 23.11.2017
  Time: 17:09
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
    <c:if test="${not empty requestScope.users}">
        <c:forEach var="user" items="${requestScope.users}">
            <p><a href="${pageContext.request.contextPath}/user?id=${user.userId}">${user.firstName} ${user.secondName}</a> ${user.guessedResultCount} ${user.guessedDiffInResultsCount} ${user.guessedWinnersCount} ${user.totalPoints}</p>
        </c:forEach>
    </c:if>
</body>
</html>
