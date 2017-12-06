<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Show User</title>
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/new-password.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/md5.js"></script>
</head>
<body>
<%@include file="header.jsp"%>

    <h2 class="form-title">Данные пользователя</h2>
    <p>Имя: <span class="span-class">${requestScope.user.firstName}</span></p>
    <p>Фамилия: <span class="span-class">${requestScope.user.secondName}</span></p>
    <p>Email: <span class="span-class">${requestScope.user.email}</span></p>
    <p>Пользователь: <span class="span-class">${requestScope.user.userState}</span></p>

    <p>Новый пароль: </p>
    <p>
    <input type="password" class="form-field" id="newPassword" name="newPassword">
    </select> <button class="btn-class" onclick="regUser()" type="submit" name="userId" id="userId" value="${requestScope.user.id}">Сохранить</button>
    </p>
    <div class="form-title" id="displayed-data"></div>

<%@include file="footer.jsp"%>
</body>
</html>
