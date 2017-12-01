<%--
  Created by IntelliJ IDEA.
  User: rizhnitsyn
  Date: 24.11.2017
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<div class="widget">
    <h3 class="widget-title">Категории</h3>
    <ul class="widget-list">
        <li><a href="${pageContext.request.contextPath}/saveUser">Регистрация</a></li>
        <br>
        <li><a href="${pageContext.request.contextPath}/tournamentList">Активные турниры</a></li>
        <br>
        <li><a href="${pageContext.request.contextPath}/userList">Авторизовать новых пользователей</a></li>
        <br>
        <li><a href="${pageContext.request.contextPath}/forecastTournaments">Новые матчи для прогнозов</a></li>
        <br>
        <li><a href="${pageContext.request.contextPath}/tournamentAllMatches">Календарь матчей</a></li>
        <br>
        <li><a href="${pageContext.request.contextPath}/tournamentStatistic">Результаты турниров</a></li>
        <br>
        <li><a href="">Правила игры</a></li>
        <br>
        <li><a href="${pageContext.request.contextPath}/user?id=${sessionScope.loggedUser.userId}">Личный кабинет</a></li>
        <br>
        <li><a href="${pageContext.request.contextPath}/login">Залогиниться</a></li>
    </ul>
</div>
</body>
</html>
