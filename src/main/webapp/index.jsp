<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>
<!DOCTYPE html>
<html>
<head>
    <title>Redirect</title>
</head>
<body>
    <% response.sendRedirect(request.getContextPath() + Path.PUBLIC_HOME); %>
</body>
</html>