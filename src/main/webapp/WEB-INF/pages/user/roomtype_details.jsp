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
    <link rel="stylesheet" href="${requestScope.basepath}/styles/main.css" type="text/css"/>
</head>
<body>

<c:import url="${Path.LOCALE_MENU_INCL}" />
<c:import url="${Path.LOGOUT_INCL}"/>

<h1><fmt:message key="locale.details.title" /></h1>

<c:if test="${not empty requestScope.roomType.image}">
    <img src="${requestScope.roomType.image}" width="250">
</c:if>

<div>
    <p><fmt:message key="locale.details.name" />: <c:out value='${requestScope.roomType.roomTypeI18n.name}'/></p>
</div>
<div>
    <p><fmt:message key="locale.details.occupancy" />: <c:out value='${requestScope.roomType.occupancy}'/></p>
</div>
<div>
    <p><fmt:message key="locale.details.description" />: <c:out value='${requestScope.roomType.roomTypeI18n.description}'/></p>
</div>
<div>
    <p><fmt:message key="locale.details.size" />: <c:out value='${requestScope.roomType.size}'/></p>
</div>

<div>
    <p><fmt:message key="locale.details.amenities" />:</p>
    <ul>
        <c:forEach var="amenity" items="${requestScope.roomType.amenities}">
            <li><c:out value="${amenity.amenityI18n.name}"/></li>
        </c:forEach>
    </ul>
</div>

<div>
    <p><fmt:message key="locale.details.totalprice" />: <c:out value='${requestScope.totalPrice}'/></p>
</div>

<h3><fmt:message key="locale.details.inputdata" />: </h3>

<c:import url="${Path.MESSAGES_INCL}" />

<form action="${requestScope.basepath}${Path.USER_BOOKING}" method="post">
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
</body>
</html>
