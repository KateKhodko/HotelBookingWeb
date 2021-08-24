<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="${Path.LOCALE_HEADER_INCL}"/>

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
        <h1><fmt:message key="locale.order.title"/></h1>
        <div class="orderInfo">
            <h2><fmt:message key="locale.order.booking"/></h2>
            <div>
                <div>
                    <p><c:out value='${requestScope.message}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.order.name"/></span>: <c:out
                            value='${requestScope.booking.roomType.roomTypeI18n.name}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.order.arrival"/></span>: <c:out
                            value='${requestScope.booking.arrivalDate}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.order.depart"/></span>: <c:out
                            value='${requestScope.booking.departDate}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.order.adults"/></span>: <c:out
                            value='${requestScope.booking.numAdults}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.order.children"/></span>: <c:out
                            value='${requestScope.booking.numChildren}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.order.totalprice"/></span>: <c:out
                            value='${requestScope.booking.price}'/> $</p>
                </div>
            </div>
        </div>
        <div class="userInfo">
            <h2><fmt:message key="locale.order.user"/></h2>
            <div>
                <div>
                    <p><span><fmt:message key="locale.order.username"/></span>: <c:out
                            value='${requestScope.booking.user.username}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.order.email"/></span>: <c:out
                            value='${requestScope.booking.user.email}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.order.firstname"/></span>: <c:out
                            value='${requestScope.booking.user.firstName}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.order.lastname"/></span>: <c:out
                            value='${requestScope.booking.user.lastName}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.order.country"/></span>: <c:out
                            value='${requestScope.booking.user.country}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.order.cardtype"/></span>: <c:out
                            value='${requestScope.booking.user.cardType}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.order.cardnumber"/></span>: <c:out
                            value='${requestScope.booking.user.cardNumber}'/></p>
                </div>
            </div>
        </div>
    </div>
    <c:import url="${Path.FOOTER_INCL}"/>
</div>


</body>
</html>
