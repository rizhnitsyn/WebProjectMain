<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Show User</title>
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="header.jsp"%>

<form action="${pageContext.request.contextPath}/user" method="post">
    <h2 class="form-title">Данные пользователя</h2>
    <p>Имя: <span class="span-class">${requestScope.user.firstName}</span></p>
    <p>Фамилия: <span class="span-class">${requestScope.user.secondName}</span></p>
    <p>Email: <span class="span-class">${requestScope.user.email}</span></p>
    <p>Пользователь: <span class="span-class">${requestScope.user.userState}</span></p>

    <p>
    <c:if test="${sessionScope.loggedUser.userStateId == 4 and sessionScope.loggedUser.userId != requestScope.user.id}">
        <select class="form-field" name="userState">
            <c:forEach var="userState" items="${requestScope.userStates}">
                <option c value="${userState.key}">${userState.value}</option>
            </c:forEach>
        </select> <button class="btn-class" type="submit" name="id" value="${requestScope.user.id}">Сменить статус пользователя</button></p>
    </c:if>

    <%@include file="footer.jsp"%>
</form>
</body>
</html>
