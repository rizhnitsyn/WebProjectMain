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
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="header.jsp"%>

    <c:if test="${not empty requestScope.matches}">
        <h2>Выберите матч для прогноза</h2>
        <div class="widget-list2">
        <c:forEach var="match" items="${requestScope.matches}">
            <p><a href="${pageContext.request.contextPath}/match?id=${match.id}">${match.firstTeam} - ${match.secondTeam}</a> <span class="span-class-list"> ${match.matchDateTime}</span></p>
        </c:forEach>
        </div>
    </c:if>

    <c:if test="${empty requestScope.matches}">
        <h3>Нет матчей для прогноза</h3>
    </c:if>
<%@include file="footer.jsp"%>
</body>
</html>
