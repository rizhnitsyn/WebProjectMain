<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 19.11.2017
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"  %>
<html>
<head>
    <title>Show tournament</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/tournament" method="post">
        <h2>Турнир: ${requestScope.tournament.name}</h2>
        <p>Дата начала игр турнира: ${requestScope.tournament.startDate}</p>
        <p>Страна организатор турнира: ${requestScope.tournament.organizerId}</p>
        <p>Состояние турнира: ${requestScope.tournament.stateId}</p>
        <button type="submit" name="id" value="${requestScope.tournament.id}">Сделать неактивным</button>
   </form>
</body>
</html>
