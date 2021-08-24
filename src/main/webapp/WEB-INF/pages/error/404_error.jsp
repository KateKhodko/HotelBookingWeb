<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="SimpleErrorTag.tld" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>

<c:import url="${Path.LOCALE_HEADER_INCL}" />

<!DOCTYPE html>
<html>
<head>
    <title>ERROR 404</title>
</head>
<body>
<main class="main">
    <section class="intro_menu">
        <div class="wrapper">
            <div class="menu_cont">
                <div class="profile_label">
                    <tag:MyTag code="404" message="Page not found."/>
                </div>
            </div>
        </div>
    </section>
</main>
</body>
</html>