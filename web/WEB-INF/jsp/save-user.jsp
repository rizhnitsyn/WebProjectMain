<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 20.11.2017
  Time: 0:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Save user</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/registration.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/md5.js"></script>
</head>
<body>
    <%--<form action="${pageContext.request.contextPath}/saveUser" method="post">--%>
        <p>Имя</p>
        <input id="firstName" name="firstName">
        <p>Фамилия</p>
        <input id="secondName" name="secondName">
        <p>Логин для входа</p>
        <input id="login" name="login">
        <p>Пароль для входа</p>
        <input id="pass" type="password" name="pass">
        <p>E-mail</p>
        <input id="email" name="email">
        <button type="submit" onclick="regUser()">Зарегистрироваться</button>
        <p id="displayed-data"></p>
    <%--</form>--%>
</body>
</html>
