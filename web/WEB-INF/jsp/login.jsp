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
</head>
<body>
    <p>Login</p><br>
    <input type="text" id="name" name="name">
    <p>Password</p><br>
    <input type="password" id="pass" name="pass">
    <button type="button" onclick="sendToServer()">Login</button>
    <%--<button type="button" >Login</button>--%>
    <p id="displayed-data"></p>
</body>
</html>
