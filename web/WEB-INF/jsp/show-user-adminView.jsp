<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 20.11.2017
  Time: 0:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Show User</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user" method="post">
    <p>Имя: ${requestScope.user.firstName}</p>
    <p>Фамилия: ${requestScope.user.secondName}</p>
    <p>Email: ${requestScope.user.email}</p>
    <p>Состояние пользователя: ${requestScope.user.userState}</p>

    <p>
    <c:if test="${sessionScope.loggedUser.userStateId == 4}">
        <select name="userState">
            <c:forEach var="userState" items="${requestScope.userStates}">
                <option value="${userState.key}">${userState.value}</option>
            </c:forEach>
        </select> <button type="submit" name="id" value="${requestScope.user.id}">Сменить статус пользователя</button></p>
    </c:if>


</form>
</body>
</html>
