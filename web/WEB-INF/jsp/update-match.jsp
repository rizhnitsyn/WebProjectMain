<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 19.11.2017
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"  %>
<html>
<head>
    <title>Update Match</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/updateMatch" method="post">
    <h2>Турнир: ${requestScope.match.tournamentId}</h2>
    <p>Начало матча: ${requestScope.match.matchDateTime}</p>
    <p>Состояние матча: ${requestScope.match.matchState}</p>
    <p>Тип матча: ${requestScope.match.matchType}</p>
    <p>Команды: ${requestScope.match.firstTeamId} - ${requestScope.match.secondTeamId}</p>
    <p>Счет:</p>
    <input id="firstTeamResult" name="firstTeamResult">
    <input id="secondTeamResult" name="secondTeamResult">
    <button type="submit" name="id" value="${requestScope.match.id}">Сохранить счет</button>
</form>
</body>
</html>
