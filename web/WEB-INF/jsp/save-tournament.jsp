<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 18.11.2017
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Save Tournament</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/saveTournament" method="post">
        <p>Добавляем новый турнир</p>
        <p>Наименование турнира</p>
        <input id="name" name="name">
        <p>Страна организатор</p>
        <select name="organizer">
            <c:forEach var="team" items="${requestScope.teams}">
                <option value="${team.id}">${team.teamName}</option>
            </c:forEach>
        </select>
        <p>Дата начала турнира</p>
        <input id="startDate" name="startDate">
        <br>
        <button type="submit">Сохранить турнир</button>
    </form>
</body>
</html>
