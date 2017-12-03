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
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="header.jsp"%>

    <h3 class="form-title">Результаты матчей</h3>
    <c:if test="${not empty requestScope.matches}">
        <div class="widget-list2">
        <c:forEach var="match" items="${requestScope.matches}">
            <p><a href="${pageContext.request.contextPath}/match?id=${match.id}">${match.firstTeam} ${match.firstTeamResult} - ${match.secondTeamResult} ${match.secondTeam}</a><span class="span-class-list"> ${match.matchDateTime}</span></p>
        </c:forEach>
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/allMatches" method="post">
    <c:if test="${requestScope.tournamentState == 1}">
        <br/><button class="btn-class" type="submit" name="idTournament" value="${requestScope.tournamentId}">Добавить новый матч </button>
    </c:if>
    </form>

<%@include file="footer.jsp"%>
</body>
</html>
