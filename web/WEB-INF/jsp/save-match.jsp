<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 19.11.2017
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <select name="matchType">
        <c:forEach var="type" items="${requestScope.matchTypes}">
            <option value="${type.key}">${type.value}</option>
        </c:forEach>
    </select>
    <p>Первая команда участник</p>
    <select name="firstTeamId">
        <c:forEach var="country" items="${requestScope.countries}">
            <option value="${country.id}">${country.teamName}</option>
        </c:forEach>
    </select>
    <p>Вторая команда участник</p>
    <select name="secondTeamId">
        <c:forEach var="country" items="${requestScope.countries}">
            <option value="${country.id}">${country.teamName}</option>
        </c:forEach>
    </select>
    <button type="submit" name="tournamentId" value="${requestScope.tournamentId}">Сохранить матч</button>
</form>

</body>
</html>
