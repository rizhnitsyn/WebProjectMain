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
        <li><a href="">Регистрация</a></li>
        <br>
        <li><a href="${pageContext.request.contextPath}/tournamentList">Заявиться на турнир</a></li>
        <br>
        <li><a href="${pageContext.request.contextPath}/userList">Подтвердить регистрацию</a></li>
        <br>
        <li><a href="${pageContext.request.contextPath}/forecastTournaments">Список матчей для прогнозов</a></li>
        <br>
        <li><a href="">Календарь матчей</a></li>
        <br>
        <li><a href="">Результаты турниров</a></li>
        <br>
        <li><a href="">Правила игры</a></li>
        <br>
        <li><a href="">Личный кабинет</a></li>
    </ul>
</div>
</body>
</html>
