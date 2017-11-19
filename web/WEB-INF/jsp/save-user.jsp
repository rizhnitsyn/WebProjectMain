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
</head>
<body>
    <form action="${pageContext.request.contextPath}/saveUser" method="post">
        <p>Имя</p>
        <input id="firstName" name="firstName">
        <p>Фамилия</p>
        <input id="secondName" name="secondName">
        <p>E-mail</p>
        <input id="email" name="email">
        <button type="submit">Зарегистрироваться</button>
    </form>
</body>
</html>
