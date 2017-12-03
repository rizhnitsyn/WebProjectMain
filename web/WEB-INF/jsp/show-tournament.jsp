<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 19.11.2017
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Show tournament</title>
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="header.jsp"%>

    <form action="${pageContext.request.contextPath}/tournament" method="post">
        <h2 class="form-title">Турнир: ${requestScope.tournament.name}</h2>
        <p>Дата начала игр турнира: <span class="span-class"> ${requestScope.tournament.startDate}</span></p>
        <p>Страна организатор турнира: <span class="span-class"> ${requestScope.tournament.teamName} </span></p>
        <p>Состояние турнира: <span class="span-class"> ${requestScope.tournament.state}</span></p>
        <p>Статус регистрации на турнир:
            <span class="span-class">
               <c:if test="${not empty requestScope.tournament.userId}"> зарегистрирован на турнир </c:if>
               <c:if test="${empty requestScope.tournament.userId}"> нет регистрации на турнир</c:if>
            </span>
        </p>
        <p>
        <c:if test="${empty requestScope.tournament.userId}">
            <button class="btn-class" type="submit" name="idReg" value="${requestScope.tournament.id}">Зарегистрироваться на турнир</button>
        </c:if>
        <c:if test="${sessionScope.loggedUser.userStateId == 4}">
            <button class="btn-class" type="submit" name="idClose" value="${requestScope.tournament.id}">Завершить турнир</button>
        </c:if>
        </p>
    </form>

    <%@include file="footer.jsp"%>
</body>
</html>
