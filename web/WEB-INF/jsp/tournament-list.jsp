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
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="header.jsp"%>

    <h2 class="form-title">Список проводящихся турниров</h2>
    <ul class="widget-list2">
        <c:forEach var="tournament" items="${requestScope.tournaments}">
            <li><a href="${pageContext.request.contextPath}/tournament?id=${tournament.id}">${tournament.name}</a><p>  Старт турнира: ${tournament.startDate},
                <c:if test="${tournament.userId == 0}">
                    <span class="span-class_red">нет регистрации</span>
                </c:if>
                <c:if test="${tournament.userId != 0}">
                    <span class="span-class_green">зарегистрирован</span>
                </c:if>
            </p></li>
        </c:forEach>
    </ul>
    <c:if test="${sessionScope.loggedUser.userStateId == 4}">
        <form action="${pageContext.request.contextPath}/tournamentList" method="post">
            <button class="btn-class" type="submit">Добавить новый турнир</button>
        </form>
    </c:if>

<%@include file="footer.jsp"%>
</body>
</html>
