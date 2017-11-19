<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 18.11.2017
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Save Tournament</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/saveTournament" method="post">
        <p>Добавляем новый турнир</p>
        <p>Наименование турнира</p>
        <input id="name" name="name">
        <p>Страна организатор турнира</p>
        <input id="organizer" name="organizer">
        <p>Дата начала турнира</p>
        <input id="startDate" name="startDate">
        <button type="submit">Сохранить турнир</button>
    </form>
</body>
</html>
