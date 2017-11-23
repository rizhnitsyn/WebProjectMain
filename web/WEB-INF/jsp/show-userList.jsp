<%--
  Created by IntelliJ IDEA.
  User: rizhnitsyn
  Date: 23.11.2017
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Show user list</title>
</head>
<body>
    <c:forEach var="user" items="${requestScope.users}">
        <p><a href="${pageContext.request.contextPath}/user?id=${user.id}">${user.firstName} ${user.secondName}</a></p>
    </c:forEach>
</body>
</html>
