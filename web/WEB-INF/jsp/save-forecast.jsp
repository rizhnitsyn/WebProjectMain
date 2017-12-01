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
</head>
<body>
<form action="${pageContext.request.contextPath}/saveForecast" method="post">
    <h2>Турнир: ${requestScope.match.tournamentName}</h2>
    <p>Начало матча: ${requestScope.match.matchDateTime}</p>
    <p>Состояние матча: ${requestScope.match.matchState}</p>
    <p>Тип матча: ${requestScope.match.matchType}</p>
    <p>Команды: ${requestScope.match.firstTeam}  <input id="firstTeamResult" name="firstTeamResult"> - <input id="secondTeamResult" name="secondTeamResult"> ${requestScope.match.secondTeam}</p>
    <button type="submit" name="id" value="${requestScope.match.id}">Сохранить прогноз</button>
</form>

</body>
</html>
