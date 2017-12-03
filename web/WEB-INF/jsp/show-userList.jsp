<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Show user list</title>
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="header.jsp"%>

    <h2 class="form-title">Список пользователей для регистрации</h2>
    <ul class="widget-list2">
        <c:forEach var="user" items="${requestScope.users}">
            <li><a href="${pageContext.request.contextPath}/user?id=${user.id}">${user.firstName} ${user.secondName}</a><li>
        </c:forEach>
    </ul>

<%@include file="footer.jsp"%>

</body>
</html>
