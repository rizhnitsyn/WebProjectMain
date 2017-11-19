<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 19.11.2017
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Save Match</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/saveMatch" method="post">
    <p>Добавляем матч в турнир</p>
    <p>Дата и время начала матча</p>
    <input id="matchDateTime" name="matchDateTime">
    <p>Тип матча</p>
    <input id="matchType" name="matchType">
    <p>Первая команда участник</p>
    <input id="firstTeamId" name="firstTeamId">
    <p>Вторая команда участник</p>
    <input id="secondTeamId" name="secondTeamId">
    <p>Турнир</p>
    <input id="tournamentId" name="tournamentId">
    <button type="submit">Сохранить матч</button>
</form>

</body>
</html>
