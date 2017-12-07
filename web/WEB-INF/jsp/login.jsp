<%@ page contentType="text/html;charset=UTF-8" %>
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

    <div class="form-title"><h2><fmt:message key="signIn"/></h2></div>
    <div class="form-title"><fmt:message key="logInInput"/></div>
    <input class="form-field" type="text" id="name" name="name"/><br />
    <div class="form-title"><fmt:message key="pass"/></div>
    <input class="form-field" type="password" id="pass" name="pass" /><br/>
    <button class="btn-class" type="button" onclick="sendToServer()"><fmt:message key="btLogIn"/></button>
    <div class="form-title" id="displayed-data"></div>
    <div class="form-title" id="displayed-data2"></div>

<%@include file="footer.jsp"%>
</body>
</html>

