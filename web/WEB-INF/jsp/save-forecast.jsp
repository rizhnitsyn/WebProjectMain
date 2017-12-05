<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Save Forecast</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/saveTournament.js"></script>
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
