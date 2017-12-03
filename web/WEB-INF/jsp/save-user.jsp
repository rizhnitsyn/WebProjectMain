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
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="header.jsp"%>

    <h2 class="form-title">Регистрация</h2>
    <p>Имя</p>
    <input class="form-field" id="firstName" name="firstName">
    <p>Фамилия</p>
    <input class="form-field" id="secondName" name="secondName">
    <p>Логин для входа</p>
    <input class="form-field" id="login" name="login">
    <p>Пароль для входа</p>
    <input class="form-field" id="pass" type="password" name="pass">
    <p>E-mail</p>
    <input class="form-field" id="email" name="email">
    <button class="btn-class" type="submit" onclick="regUser()">Зарегистрироваться</button>
    <p id="displayed-data"></p>

<%@include file="footer.jsp"%>
</body>
</html>
