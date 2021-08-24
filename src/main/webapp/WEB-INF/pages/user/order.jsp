<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>

<c:import url="${Path.LOCALE_HEADER_INCL}" />

<html>
<head>
    <title>Order</title>
    <link rel="stylesheet" href="${requestScope.basepath}/styles/main.css" type="text/css"/>
</head>
<body>

<c:import url="${Path.LOCALE_MENU_INCL}" />
<c:import url="${Path.LOGOUT_INCL}"/>

<h1><fmt:message key="locale.public.order.title" /></h1>

<div>
    <p><c:out value='${requestScope.message}'/></p>
</div>
<div>
    <p><fmt:message key="locale.public.order.name" />: <c:out value='${requestScope.roomType.roomTypeI18n.name}'/></p>
</div>
<div>
    <p><fmt:message key="locale.public.order.arrival" />: <c:out value='${requestScope.booking.arrivalDate}'/></p>
</div>
<div>
    <p><fmt:message key="locale.public.order.depart" />: <c:out value='${requestScope.booking.departDate}'/></p>
</div>
<div>
    <p><fmt:message key="locale.public.order.adults" />: <c:out value='${requestScope.booking.numAdults}'/></p>
</div>
<div>
    <p><fmt:message key="locale.public.order.children" />: <c:out value='${requestScope.booking.numChildren}'/></p>
</div>
<div>
    <p><fmt:message key="locale.public.order.email" />: <c:out value='${requestScope.authUser.email}'/></p>
</div>
<div>
    <p><fmt:message key="locale.public.order.firstname" />: <c:out value='${requestScope.authUser.firstName}'/></p>
</div>
<div>
    <p><fmt:message key="locale.public.order.lastname" />: <c:out value='${requestScope.authUser.lastName}'/></p>
</div>
<div>
    <p><fmt:message key="locale.public.order.country" />: <c:out value='${requestScope.authUser.country}'/></p>
</div>
<div>
    <p><fmt:message key="locale.public.order.totalprice" />: <c:out value='${requestScope.booking.price}'/></p>
</div>

</body>
</html>
