<%--
  Created by IntelliJ IDEA.
  User: rizhnitsyn
  Date: 20.11.2017
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Save Forecast</title>
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="header.jsp"%>

<form action="${pageContext.request.contextPath}/saveForecast" method="post">
    <h2 class="form-title">Турнир :  ${requestScope.match.tournamentName}</h2>
    <p>Начало матча: <span class="span-class">${requestScope.match.matchDateTime}</span></p>
    <p>Тип матча: <span class="span-class">${requestScope.match.matchType}</span></p>
    <p>Команды: <span class="span-class">${requestScope.match.firstTeam} -  ${requestScope.match.secondTeam}</span></p>
    <p>Прогнозируемый счет:</p>
    <p><input class="form-field" id="firstTeamResult" name="firstTeamResult"> - <input class="form-field" id="secondTeamResult" name="secondTeamResult"></p>
    <button class="btn-class" type="submit" name="id" value="${requestScope.match.id}">Сохранить прогноз</button>
</form>
<%@include file="footer.jsp"%>

</body>
</html>
