<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 20.11.2017
  Time: 0:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Show User</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user" method="post">
    <p>Имя: ${requestScope.user.firstName}</p>
    <p>Фамилия: ${requestScope.user.secondName}</p>
    <p>Email: ${requestScope.user.email}</p>
    <p>Состояние пользователя ${requestScope.user.userState}</p>
    <button type="submit" name="idReg" value="${requestScope.user.id}">Подтвердить регистрацию</button>
    <button type="submit" name="idBlock" value="${requestScope.user.id}">Заблокировать</button>
</form>
</body>
</html>
