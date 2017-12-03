
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Save Tournament</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/saveTournament.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="header.jsp"%>

        <h2 class="form-title">Добавляем новый турнир</h2>
        <p>Наименование турнира</p>
        <input class="form-field" id="name" name="name">
        <p>Страна организатор</p>
        <select class="form-field" id="organizerId" name="organizerId">
            <c:forEach var="team" items="${requestScope.teams}">
                <option value="${team.id}">${team.teamName}</option>
            </c:forEach>
        </select>
        <p>Дата начала турнира</p>
        <input class="form-field" type="date" id="startDate" name="startDate">
        <br>
        <button class="btn-class" type="submit" onclick="sendToServer()">Сохранить турнир</button>
        <p id="displayed-data"></p>

    <%@include file="footer.jsp"%>
</body>
</html>
