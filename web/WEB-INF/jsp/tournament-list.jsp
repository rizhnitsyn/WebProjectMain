<%--
  Created by IntelliJ IDEA.
  User: rizhnitsyn
  Date: 22.11.2017
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Tournament List</title>
</head>
<body>
  список всех активных турниров на текущий момент
<c:if test="${sessionScope.loggedUser.userStateId == 4}">
    <form action="${pageContext.request.contextPath}/tournamentList" method="post">
       <button type="submit">Добавить новый турнир</button>
    </form>
</c:if>
    <c:forEach var="tournament" items="${requestScope.tournaments}">
        <p><a href="${pageContext.request.contextPath}/tournament?id=${tournament.id}">${tournament.name}</a>  Начало: ${tournament.startDate} Состояние: ${tournament.state}</p>
    </c:forEach>
</body>
</html>
