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
    <table class="simple-little-table">
        <tr>
            <th>Матч</th>
            <th>Счет</th>
            <th>Прогноз</th>
            <th>Очки за матч</th>
            <th>Дата матча</th>
        </tr>
        <c:forEach var="match" items="${requestScope.matches}">
            <tr class="widget-list3">
                <td><a href="${pageContext.request.contextPath}/match?id=${match.id}">${match.firstTeam} - ${match.secondTeam}</a></td>
                <td>
                    <c:if test="${not empty match.firstTeamResult}">
                        <span>${match.firstTeamResult} - ${match.secondTeamResult}</span>
                    </c:if>
                    <c:if test="${empty match.firstTeamResult}">
                        <span class="span-class_green">ожидается</span>
                    </c:if>
                </td>
                <td>
                    <c:if test="${not empty match.currentUserForecast.firstTeamForecast}">
                        <span>${match.currentUserForecast.firstTeamForecast} - ${match.currentUserForecast.secondTeamForecast}</span>
                    </c:if>
                    <c:if test="${empty match.currentUserForecast.firstTeamForecast}">
                        <span class="span-class_red">нет прогноза</span>
                    </c:if>
                </td>
                <td>${match.userPoints}</td>
                <td>${match.strMatchDateTime}</td>
            </tr>
        </c:forEach>
    </table>
    </c:if>

    <form action="${pageContext.request.contextPath}/allMatches" method="post">
    <c:if test="${requestScope.tournamentState == 1}">
        <br/><button class="btn-class" type="submit" name="idTournament" value="${requestScope.tournamentId}">Добавить новый матч </button>
    </c:if>
    </form>

<%@include file="footer.jsp"%>
</body>
</html>
