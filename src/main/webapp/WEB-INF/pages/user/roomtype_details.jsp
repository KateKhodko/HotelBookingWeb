<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>
<%@ page import="com.khodko.RoyalHotel.form.BookingForm" %>

<c:import url="${Path.LOCALE_HEADER_INCL}" />

<!DOCTYPE html>
<html>
<head>
    <title>Details</title>
    <link rel="stylesheet" href="${requestScope.basepath}/styles/main1.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/header.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/roomtypeDetails1.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/footer1.css" type="text/css"/>
</head>
<body>

<c:import url="${Path.HEADER_INCL}"/>

<img src="${basepath}/images/roomtype.jpeg" class="img">
<div class="overlay">
    <h1><fmt:message key="locale.details.title" /></h1>
</div>

<div class="detailsBox">
    <div class="room">
        <div class="image">
            <c:if test="${not empty requestScope.roomType.image}">
                <img src="${requestScope.roomType.image}" width="250">
            </c:if>
        </div>
        <div class="details">
            <h3><c:out value='${requestScope.roomType.roomTypeI18n.name}'/></h3>
            <div>
                <p><fmt:message key="locale.details.occupancy" />: <c:out value='${requestScope.roomType.occupancy}'/></p>
            </div>
            <div>
                <p><fmt:message key="locale.details.size" />: <c:out value='${requestScope.roomType.size}'/> square metres</p>
            </div>
            <div>
                <p><fmt:message key="locale.details.description" />: <span class="description"><c:out value='${requestScope.roomType.roomTypeI18n.description}'/></span></p>
            </div>
        </div>
    </div>


    <div class="amenities">
        <h3><fmt:message key="locale.details.amenities" />:</h3>
        <ul>
            <c:forEach var="amenity" items="${requestScope.roomType.amenities}">
                <li><c:out value="${amenity.amenityI18n.name}"/></li>
            </c:forEach>
        </ul>
    </div>

    <div class="totalPrice">
        <p class="priceName"><fmt:message key="locale.details.totalprice" /></p>
        <p class="priceValue"><c:out value='${requestScope.totalPrice}'/> $</p>
    </div>
</div>

<div class="dateFilter">
    <h2><fmt:message key="locale.details.inputdata" />: </h2>
    <div class="formBox">
        <form action="${requestScope.basepath}${Path.USER_BOOKING}" method="post" class="dateForm">
            <input type="hidden" name="${BookingForm.idName}" value="<c:out value='${requestScope.roomType.id}'/>" >
            <div>
                <label for="date"><fmt:message key="locale.details.arrival" />: </label>
                <input id="date" type="date" name="${BookingForm.dateName}" value="<c:out value='${requestScope.bookingForm.dateValue}'/>">
            </div>
            <div>
                <label for="days"><fmt:message key="locale.details.days" />: </label>
                <input id="days" type="number" name="${BookingForm.daysName}" min="0" value="<c:out value='${requestScope.bookingForm.daysValue}'/>">
            </div>
            <div>
                <label for="adults"><fmt:message key="locale.details.adults" />: </label>
                <input id="adults" type="number" max="${requestScope.roomType.occupancy}" min="0" name="${BookingForm.adultsName}" value="<c:out value='${requestScope.bookingForm.adultsValue}'/>">
            </div>
            <div>
                <label for="children"><fmt:message key="locale.details.children" />: </label>
                <input id="children" type="number" max="${requestScope.roomType.occupancy}" min="0" name="${BookingForm.childrenName}" value="<c:out value='${requestScope.bookingForm.childrenValue}'/>">
            </div>
            <button type="submit"><fmt:message key="locale.details.booking" /></button>
        </form>
    </div>
    <c:import url="${Path.MESSAGES_INCL}" />
</div>
<c:import url="${Path.FOOTER_INCL}"/>

</body>
</html>
