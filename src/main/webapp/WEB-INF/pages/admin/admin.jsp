<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="${Path.LOCALE_HEADER_INCL}" />

<!DOCTYPE html>
<html>
<head>
    <title>Admin main</title>
    <link rel="stylesheet" href="${basepath}/styles/main.css" type="text/css"/>
</head>
<body>

<c:import url="${Path.LOCALE_MENU_INCL}" />
<c:import url="${Path.LOGOUT_INCL}" />
<c:import url="${Path.ADMIN_MENU_INCL}"/>


<h1><fmt:message key="locale.admin.title" /></h1>

</body>
</html>