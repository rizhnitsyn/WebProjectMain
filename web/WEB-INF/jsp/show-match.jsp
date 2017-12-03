<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 19.11.2017
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Show Match</title>
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="header.jsp"%>

<form action="${pageContext.request.contextPath}/match" method="post">
    <h3 class="form-title">Турнир: ${requestScope.match.tournamentName}</h3>
    <p>Начало матча: <span class="span-class">${requestScope.match.matchDateTime}, ${requestScope.match.matchState}</span></p>
    <%--<p>Состояние матча: <span class="span-class">${requestScope.match.matchState}</span></p>--%>
    <p>Тип матча: <span class="span-class">${requestScope.match.matchType}</span></p>
    <p>Команды: <span class="span-class">${requestScope.match.firstTeam} - ${requestScope.match.secondTeam}</span></p>
    <p>Счет матча: <span class="span-class">${requestScope.match.firstTeamResult} - ${requestScope.match.secondTeamResult}</span></p>
    <p>Мой прогноз: <span class="span-class">${requestScope.match.currentUserForecast.firstTeamForecast} - ${requestScope.match.currentUserForecast.secondTeamForecast}</span></p>
    <p>Всего прогнозов на матч: <span class="span-class">${requestScope.match.forecastsCount}</span></p>
    <h3 class="form-title">Предматчевая статистика</h3>
    <p>Прогнозов на победу первой команды: <span class="span-class">${requestScope.match.firstTeamWinCount}</span></p>
    <p>Прогнозов на победу второй команды: <span class="span-class">${requestScope.match.secondTeamWinCount}</span></p>
    <p>Прогнозов на ничью:<span class="span-class"> ${requestScope.match.drawCount}</span></p>
    <h3 class="form-title">Статистика по результатам матча</h3>
    <p>Угаданн счет в матче, количество: <span class="span-class">${requestScope.match.guessedResultsCount}</span></p>
    <p>Угадан исход матча, количество: <span class="span-class">${requestScope.match.guessedWinnersCount}</span></p>
    <p>Угадана разница счета, количество: <span class="span-class">${requestScope.match.guessedDiffInResultsCount}</span></p>
    <p>Получено очков в этом матче: <span class="span-class">${requestScope.match.currentUserPoints} </span></p>
    <c:if test="${requestScope.isActive}">
        <button class="btn-class" type="submit" name="idForecast" value="${requestScope.match.id}">Сделать прогноз на матч</button>
    </c:if>
    <c:if test="${sessionScope.loggedUser.userStateId == 4}">
         <button class="btn-class" type="submit" name="idMatch" value="${requestScope.match.id}">Внести счет матча</button>
    </c:if>
<%@include file="footer.jsp"%>
</form>
</body>
</html>
