
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</main><!-- .content -->
</div><!-- .container-->

<aside class="left-sidebar">
    <div class="widget">
        <h3 class="widget-title">Категории</h3>
        <ul class="widget-list">
            <li><a href="${pageContext.request.contextPath}/tournamentList">Регистрация на турнир</a></li>
            <c:if test="${sessionScope.loggedUser != null && sessionScope.loggedUser.userStateId == 4}">
                <li><a href="${pageContext.request.contextPath}/userList">Авторизовать пользователей</a></li>
            </c:if>
            <li><a href="${pageContext.request.contextPath}/forecastTournaments">Сделать прогноз</a></li>
            <li><a href="${pageContext.request.contextPath}/tournamentAllMatches">Результаты матчей</a></li>
            <li><a href="${pageContext.request.contextPath}/tournamentStatistic">Результаты турниров</a></li>
            <li><a href="${pageContext.request.contextPath}/homepage">Правила игры</a></li>
            <li><a href="${pageContext.request.contextPath}/user?id=${sessionScope.loggedUser.userId}">Личный кабинет</a></li>
            <li><a href="${pageContext.request.contextPath}/saveUser">Регистрация</a></li>
            <c:if test="${sessionScope.loggedUser == null}">
                <li><a href="${pageContext.request.contextPath}/login">Войти</a></li>
            </c:if>
            <c:if test="${sessionScope.loggedUser != null}">
                <li><a href="${pageContext.request.contextPath}/logout">Выйти(${sessionScope.loggedUser.secondName})</a></li>
            </c:if>
        </ul>
    </div>
</aside><!-- .left-sidebar -->
</div><!-- .middle-->
</div><!-- .wrapper -->

<footer class="footer">
    <div>${sessionScope.loggedUser.firstName} ${sessionScope.loggedUser.secondName}             <span class="footer-class2"><a
        href="">RU</a>  <a href="">EN</a></span></div>
</footer><!-- .footer -->
