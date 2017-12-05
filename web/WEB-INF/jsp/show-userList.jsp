<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Show user list</title>
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="header.jsp"%>

    <h2 class="form-title">Список пользователей</h2>
    <ul class="widget-list4">
        <c:forEach var="user" items="${requestScope.users}">
            <li><a href="${pageContext.request.contextPath}/user?id=${user.id}">${user.firstName} ${user.secondName}</a>
                <c:if test="${user.userStateId  == 1}"><span class="span-class_red">${user.userState}</span></c:if>
                <c:if test="${user.userStateId  == 2}"><span class="span-class_green">${user.userState}</span></c:if>
                <c:if test="${user.userStateId  == 3}"><span class="span-class_red">${user.userState}</span></c:if>
                <c:if test="${user.userStateId  == 4}"><span class="span-class_green">${user.userState}</span></c:if>
            <li>
        </c:forEach>
    </ul>

<%@include file="footer.jsp"%>

</body>
</html>
