<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <c:when test="${not empty sessionUser}">
        <p class="loginLink"><a href="${basepath}${Path.PUBLIC_LOGOUT}"><fmt:message key="locale.logout.logout"/></a></p>
    </c:when>
    <c:otherwise>
        <p>
            <a class="loginLink" href="${basepath}${Path.PUBLIC_SHOW_SIGNUP}"><fmt:message key="locale.logout.signup"/> </a>
            <fmt:message key="locale.logout.or"/>
            <a class="loginLink" href="${basepath}${Path.PUBLIC_SHOW_LOGIN}"> <fmt:message key="locale.logout.login"/></a>
        </p>
    </c:otherwise>
</c:choose>