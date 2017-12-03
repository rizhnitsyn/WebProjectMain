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
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/saveMatch.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="header.jsp"%>

    <h3 class="form-title">Добавляем матч в турнир</h3>
    <div class="form-title">Дата и время начала матча</div>
    <input class="form-field" type="datetime-local" id="matchDateTime" name="matchDateTime">
    <div class="form-title">Тип матча</div>
    <select class="form-field" id="matchType" name="matchType">
        <c:forEach var="type" items="${requestScope.matchTypes}">
            <option value="${type.key}">${type.value}</option>
        </c:forEach>
    </select>
    <div class="form-title">Первая команда участник</div>
    <select class="form-field" id="firstTeam" name="firstTeam">
        <c:forEach var="country" items="${requestScope.countries}">
            <option value="${country.id}">${country.teamName}</option>
        </c:forEach>
    </select>
    <div class="form-title">Вторая команда участник</div>
    <select class="form-field" id="secondTeam" name="secondTeam">
        <c:forEach var="country" items="${requestScope.countries}">
            <option value="${country.id}">${country.teamName}</option>
        </c:forEach>
    </select>
    <button class="btn-class" type="submit" id="tournamentId" name="tournamentId" value="${requestScope.tournamentId}" onclick="sendToServer()">Сохранить матч</button>
    <div class="form-title" id="displayed-data"></div>
<%--</form>--%>
<%@include file="footer.jsp"%>

</body>
</html>
