<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>

<c:import url="${Path.LOCALE_HEADER_INCL}"/>

<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="${basepath}/styles/main.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/header.css" type="text/css"/>
</head>
<body>
<header>
    <div class="menu">
        <div class="menuContainer">
            <c:import url="${Path.LOGOUT_INCL}"/>
            <ul class="mainMenu">
                <li><a href="${requestScope.basepath}${Path.ADMIN_MAIN}"><fmt:message key="locale.home.admin"/></a></li>
                <li><a href="${requestScope.basepath}${Path.USER_PROFILE}"><fmt:message key="locale.home.user"/></a></li>
                <li><a href="${requestScope.basepath}${Path.PUBLIC_ROOMTYPES}"><fmt:message
                        key="locale.home.roomtypes"/></a></li>
            </ul>
        </div>
        <div class="langMenu">
            <span>LANGUAGE</span>
            <c:import url="${Path.LOCALE_MENU_INCL}"/>
        </div>
    </div>
    <h1>"<fmt:message key="locale.home.title"/>!"</h1>
</header>
</body>
</html>