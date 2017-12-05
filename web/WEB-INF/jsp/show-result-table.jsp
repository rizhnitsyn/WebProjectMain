<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Matches of Tournament</title>
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="header.jsp"%>

    <h2 class="form-title">Результаты турнира ${requestScope.tournament.name}</h2>
    <c:if test="${not empty requestScope.users}">
        <table class="simple-little-table">
        <caption>Общий итог</caption>
            <tr>
                <th>ФИО</th>
                <th>6 очков</th>
                <th>4 очка</th>
                <th>3 очка</th>
                <th>1 очко</th>
                <th>БАЛЛЫ</th>
            </tr>
        <c:forEach var="user" items="${requestScope.users}">
            <tr class="widget-list3">
                <td><a href="${pageContext.request.contextPath}/allUserForecasts?userId=${user.userId}&tournamentId=${user.tournamentId}">${user.firstName} ${user.secondName}</a></td>
                <td>${user.guessedResultCount}</td>
                <td>${user.guessedDiffInResultsCount}</td>
                <td>${user.guessedDrawCount}</td>
                <td>${user.guessedWinnersCount}</td>
                <td>${user.totalPoints}</td>
            </tr>
        </c:forEach>
        </table>
    </c:if>
<%@include file="footer.jsp"%>

</body>
</html>
