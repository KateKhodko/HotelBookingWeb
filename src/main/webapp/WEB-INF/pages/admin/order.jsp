<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="${Path.LOCALE_HEADER_INCL}" />

<html>
<head>
    <title>Order</title>
    <link rel="stylesheet" href="${requestScope.basepath}/styles/main.css" type="text/css"/>
</head>
<body>

<c:import url="${Path.LOCALE_MENU_INCL}" />
<c:import url="${Path.LOGOUT_INCL}"/>

<h1><fmt:message key="locale.order.title" /></h1>

<h2><fmt:message key="locale.order.booking" /></h2>
<div>
    <p><c:out value='${requestScope.message}'/></p>
</div>
<div>
    <p><fmt:message key="locale.order.name" />: <c:out value='${requestScope.booking.roomType.roomTypeI18n.name}'/></p>
</div>
<div>
    <p><fmt:message key="locale.order.arrival" />: <c:out value='${requestScope.booking.arrivalDate}'/></p>
</div>
<div>
    <p><fmt:message key="locale.order.depart" />: <c:out value='${requestScope.booking.departDate}'/></p>
</div>
<div>
    <p><fmt:message key="locale.order.adults" />: <c:out value='${requestScope.booking.numAdults}'/></p>
</div>
<div>
    <p><fmt:message key="locale.order.children" />: <c:out value='${requestScope.booking.numChildren}'/></p>
</div>
<div>
    <p><fmt:message key="locale.order.totalprice" />: <c:out value='${requestScope.booking.price}'/></p>
</div>
<hr>
<h2><fmt:message key="locale.order.user" /></h2>
<div>
    <p><fmt:message key="locale.order.username" />: <c:out value='${requestScope.booking.user.username}'/></p>
</div>
<div>
    <p><fmt:message key="locale.order.email" />: <c:out value='${requestScope.booking.user.email}'/></p>
</div>
<div>
    <p><fmt:message key="locale.order.firstname" />: <c:out value='${requestScope.booking.user.firstName}'/></p>
</div>
<div>
    <p><fmt:message key="locale.order.lastname" />: <c:out value='${requestScope.booking.user.lastName}'/></p>
</div>
<div>
    <p><fmt:message key="locale.order.country" />: <c:out value='${requestScope.booking.user.country}'/></p>
</div>
<div>
    <p><fmt:message key="locale.order.cardtype" />: <c:out value='${requestScope.booking.user.cardType}'/></p>
</div>
<div>
    <p><fmt:message key="locale.order.cardnumber" />: <c:out value='${requestScope.booking.user.cardNumber}'/></p>
</div>

</body>
</html>
