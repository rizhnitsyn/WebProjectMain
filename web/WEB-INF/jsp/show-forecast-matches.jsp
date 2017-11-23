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
    <title>Matches for Forecast</title>
</head>
<body>

    <c:if test="${not empty requestScope.matches}">
        <c:forEach var="match" items="${requestScope.matches}">
            <p><a href="${pageContext.request.contextPath}/match?id=${match.id}">${match.firstTeam} - ${match.secondTeam}</a> ${match.matchDateTime}</p>
        </c:forEach>
    </c:if>

    <c:if test="${empty requestScope.matches}">
        <p>Нет матчей для прогноза</p>
    </c:if>
</body>
</html>
