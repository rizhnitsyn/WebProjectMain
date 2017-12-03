<%--
  Created by IntelliJ IDEA.
  User: rizhnitsyn
  Date: 30.11.2017
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/login.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/md5.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="header.jsp"%>

    <div class="form-title"><h2>Вход</h2></div>
    <div class="form-title">Логин</div>
    <input class="form-field" type="text" id="name" name="name"/><br />
    <div class="form-title">Пароль</div>
    <input class="form-field" type="password" id="pass" name="pass" /><br/>
    <button class="btn-class" type="button" onclick="sendToServer()">Войти</button>
    <div class="form-title" id="displayed-data"></div>

<%@include file="footer.jsp"%>
</body>
</html>

