<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 19.11.2017
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"  %>
<html>
<head>
    <title>Show Match</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/match" method="post">
    <h2>Турнир: ${requestScope.match.tournamentName}</h2>
    <p>Начало матча: ${requestScope.match.matchDateTime}</p>
    <p>Состояние матча: ${requestScope.match.matchState}</p>
    <p>Тип матча: ${requestScope.match.matchType}</p>
    <p>Команды: ${requestScope.match.firstTeam} - ${requestScope.match.secondTeam}</p>
    <p>Счет матча ${requestScope.match.firstTeamResult} - ${requestScope.match.secondTeamResult}</p>
    <p>Мой прогноз ${requestScope.match.currentUserForecast.firstTeamForecast} - ${requestScope.match.currentUserForecast.secondTeamForecast}</p>
    <p>Всего прогнозов на матч ${requestScope.match.forecastsCount}</p>
    <p>Предматчевая статистика</p>
    <p>Прогнозов на победу первой команды ${requestScope.match.firstTeamWinCount}</p>
    <p>Прогнозов на победу второй команды ${requestScope.match.secondTeamWinCount}</p>
    <p>Прогнозов на ничью ${requestScope.match.drawCount}</p>
    <p>Статистика по результатам матча:</p>
    <p>Угаданн счет в матче, количество ${requestScope.match.guessedResultsCount}</p>
    <p>Угадан исход матча, количество: ${requestScope.match.guessedWinnersCount}</p>
    <p>Угадана разница счета, количество: ${requestScope.match.guessedDiffInResultsCount}</p>
    <p>Получено очков в этом матче: ${requestScope.match.currentUserPoints} </p>
    <button type="submit" name="idMatch" value="${requestScope.match.id}">Изменить счет матча</button>
    <button type="submit" name="idForecast" value="${requestScope.match.id}">Сохранить прогноз</button>
</form>
</body>
</html>
