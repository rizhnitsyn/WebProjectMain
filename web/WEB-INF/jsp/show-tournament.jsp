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
</head>
<body>
    <form action="${pageContext.request.contextPath}/tournament" method="post">
        <h2>Турнир: ${requestScope.tournament.name}</h2>
        <p>Дата начала игр турнира: ${requestScope.tournament.startDate}</p>
        <p>Страна организатор турнира: ${requestScope.tournament.teamName}</p>
        <p>Состояние турнира: ${requestScope.tournament.state}</p>
        <p>Статус регистрации на турнир:
            <c:if test="${not empty requestScope.tournament.userId}"> Зарегистрирован на турнир </c:if>
            <c:if test="${empty requestScope.tournament.userId}"> Нет регистрации на турнир
               <p><button type="submit" name="idReg" value="${requestScope.tournament.id}">Зарегистрироваться на турнир</button> </p>
            </c:if>
        </p>
        <c:if test="${sessionScope.loggedUser.userStateId == 4}">
            <button type="submit" name="idClose" value="${requestScope.tournament.id}">Завершить турнир</button>
        </c:if>

   </form>
</body>
</html>
