<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>

<!--<h3><fmt:message key="locale.user.menu.name" /></h3>-->
<ul class="userItems">
    <li><a href="${requestScope.basepath}${Path.USER_ORDERS}"><fmt:message key="locale.user.menu.orders" /></a></li>
    <li><a href="${requestScope.basepath}${Path.USER_PROFILE}"><fmt:message key="locale.user.menu.profile" /></a></li>
</ul>
