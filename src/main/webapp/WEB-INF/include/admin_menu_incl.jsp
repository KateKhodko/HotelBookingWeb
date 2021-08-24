<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>

<!--<h3><fmt:message key="locale.admin.menu.name" /></h3>-->
<ul class="userItems">
	<li><a href="${requestScope.basepath}${Path.ADMIN_LANGS}">Languages</a></li>
    <li><a href="${requestScope.basepath}${Path.ADMIN_ORDERS}"><fmt:message key="locale.admin.menu.orders" /></a></li>
    <li><a href="${requestScope.basepath}${Path.ADMIN_ROOMTYPES}"><fmt:message key="locale.admin.menu.roomtypes" /></a></li>
    <li><a href="${requestScope.basepath}${Path.ADMIN_AMENITIES}"><fmt:message key="locale.admin.menu.amenities" /></a></li>
</ul>
