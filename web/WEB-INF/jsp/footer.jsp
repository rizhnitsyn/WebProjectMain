<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

</main><!-- .content -->
</div><!-- .container-->

<aside class="left-sidebar">
    <div class="widget">
        <h3 class="widget-title"><fmt:message key="categories"/></h3>
        <ul class="widget-list">
            <li><a href="${pageContext.request.contextPath}/tournamentList"><fmt:message key="regOnTournament"/></a></li>
            <c:if test="${sessionScope.loggedUser != null && sessionScope.loggedUser.userStateId == 4}">
                <li><a href="${pageContext.request.contextPath}/userList"><fmt:message key="userList"/></a></li>
            </c:if>
            <li><a href="${pageContext.request.contextPath}/forecastTournaments"><fmt:message key="createForecast"/></a></li>
            <li><a href="${pageContext.request.contextPath}/tournamentAllMatches"><fmt:message key="myForecasts"/></a></li>
            <li><a href="${pageContext.request.contextPath}/tournamentStatistic"><fmt:message key="tournamentsResult"/></a></li>
            <li><a href="${pageContext.request.contextPath}/homepage"><fmt:message key="rules"/></a></li>
            <li><a href="${pageContext.request.contextPath}/user?id=${sessionScope.loggedUser.userId}"><fmt:message key="privateAccount"/></a></li>
            <li><a href="${pageContext.request.contextPath}/saveUser"><fmt:message key="regInSystem"/></a></li>
            <c:if test="${sessionScope.loggedUser == null}">
                <li><a href="${pageContext.request.contextPath}/login"><fmt:message key="loginUser"/></a></li>
            </c:if>
            <c:if test="${sessionScope.loggedUser != null}">
                <li><a href="${pageContext.request.contextPath}/logout"><fmt:message key="logoutUser"/>(${sessionScope.loggedUser.secondName})</a></li>
            </c:if>
        </ul>
    </div>
</aside><!-- .left-sidebar -->
</div><!-- .middle-->
</div><!-- .wrapper -->

<footer class="footer">
    <div><fmt:message key="currentUser"/>:  ${sessionScope.loggedUser.firstName} ${sessionScope.loggedUser.secondName}             <span class="footer-class2"><a
        href="${pageContext.request.contextPath}/locale?name=ru">RU</a>  <a href="${pageContext.request.contextPath}/locale?name=en">EN</a></span></div>
</footer><!-- .footer -->
