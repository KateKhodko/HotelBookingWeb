<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>

<c:import url="${Path.LOCALE_HEADER_INCL}" />

<html>
<head>
    <title>Order</title>
    <link rel="stylesheet" href="${requestScope.basepath}/styles/main1.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/header.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/profile1.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/footer1.css" type="text/css"/>
</head>
<body>

<c:import url="${Path.HEADER_INCL}"/>

<img class="image" src="${basepath}/images/bg.jpeg">
<div class="overlay">
    <div class="dataOverlay">
        <h1><fmt:message key="locale.public.order.title" /></h1>
        <div class="orderInfo">
            <h2>Basic info</h2>
            <div>
                <div>
                    <p><span><fmt:message key="locale.public.order.name" /></span>: <c:out value='${requestScope.roomType.roomTypeI18n.name}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.public.order.arrival" /></span>: <c:out value='${requestScope.booking.arrivalDate}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.public.order.depart" /></span>: <c:out value='${requestScope.booking.departDate}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.public.order.adults" /></span>: <c:out value='${requestScope.booking.numAdults}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.public.order.children" /></span>: <c:out value='${requestScope.booking.numChildren}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.public.order.email" /></span>: <c:out value='${requestScope.authUser.email}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.public.order.firstname" /></span>: <c:out value='${requestScope.authUser.firstName}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.public.order.lastname" /></span>: <c:out value='${requestScope.authUser.lastName}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.public.order.country" /></span>: <c:out value='${requestScope.authUser.country}'/></p>
                </div>
                <div>
                    <p><c:out value='${requestScope.message}'/></p>
                </div>
            </div>
        </div>

        <div class="totalPrice">
            <h2><fmt:message key="locale.public.order.totalprice" /></h2>
            <div>
                <p><c:out value='${requestScope.booking.price}'/> $</p>
            </div>
        </div>
    </div>
    <c:import url="${Path.FOOTER_INCL}"/>
</div>


</body>
</html>
