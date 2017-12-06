
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${sessionScope.locale == null}">
    <fmt:setLocale value="ru"/>
</c:if>
<c:if test="${sessionScope.locale == 'ru'}">
    <fmt:setLocale value="ru"/>
</c:if>
<c:if test="${sessionScope.locale == 'en'}">
    <fmt:setLocale value="en"/>
</c:if>
<fmt:setBundle basename="translations"/>

<div class="wrapper">
    <header class="header">
        <img src="${pageContext.request.contextPath}/resources/jpg/Header_new.jpg" alt="" class="headjpg"/>
    </header><!-- .header-->

    <div class="middle">
        <div class="container">
            <main class="content">


