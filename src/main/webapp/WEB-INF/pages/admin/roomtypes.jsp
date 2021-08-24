<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>
<%@ page import="com.khodko.RoyalHotel.form.RoomTypeFilterForm" %>
<%@ page import="com.khodko.RoyalHotel.form.OrderByForm" %>

<c:import url="${Path.LOCALE_HEADER_INCL}"/>

<!DOCTYPE html>
<html>
<head>
    <title>Room Types</title>
    <link rel="stylesheet" href="${basepath}/styles/main1.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/header.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/roomTypes1.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/footer1.css" type="text/css"/>
</head>
<body>

<c:import url="${Path.HEADER_INCL}"/>

<img src="${basepath}/images/rooms.jpeg" class="img">
<div class="overlay">
    <h1><fmt:message key="locale.roomtypes.title"/></h1>
</div>

<div class="dateFilter">
    <h2>Check availability</h2>
    <div class="formBox">
        <form action="${requestScope.basepath}${Path.ADMIN_ROOMTYPES}" method="get" class="dateForm">
            <div>
                <label for="arrival_date">Check in: </label>
                <input id="arrival_date" type="date" name="${RoomTypeFilterForm.arrivalDateName}"
                       value="<c:out value='${sessionScope.roomTypeFilterForm.arrivalDateValue}'/>">
            </div>
            <div>
                <label for="depart_date">Check out: </label>
                <input id="depart_date" type="date" name="${RoomTypeFilterForm.departDateName}"
                       value="<c:out value='${sessionScope.roomTypeFilterForm.departDateValue}'/>">
            </div>
            <input class="check" type="submit" name="${RoomTypeFilterForm.filterBtnName}" value="Check"/>
        </form>
        <form action="${requestScope.basepath}${Path.ADMIN_ROOMTYPES}" method="get">
            <input class="reset" type="submit" name="${RoomTypeFilterForm.resetBtnName}" value="Reset"/>
        </form>
    </div>
    <c:import url="${Path.MESSAGES_INCL}"/>
    <p class="addRoom">
        <a href="${basepath}<%=Path.ADMIN_ROOMTYPE%>"><fmt:message key="locale.roomtypes.add"/></a>
    <p>
</div>

<div class="roomTypes">

    <c:forEach var="roomType" items="${roomTypes}">
        <div class="room">
            <div class="image">
                <c:if test="${not empty roomType.image}">
                    <img src="${roomType.image}" alt="${roomType.roomTypeI18n.name}" width="50" height="50">
                </c:if>
            </div>
            <div class="details">
                <h3><c:out value="${roomType.roomTypeI18n.name}"/></h3>
                <p><fmt:message key="locale.roomtypes.occupancy"/> : <c:out value="${roomType.occupancy}"/></p>
                <p><fmt:message key="locale.roomtypes.size"/> : <c:out value="${roomType.size}"/> square metres</p>
                <p><fmt:message key="locale.roomtypes.baseprice"/> : <c:out value="${roomType.price}"/> $</p>
                <p><fmt:message key="locale.roomtypes.description"/> : <c:out
                        value="${roomType.roomTypeI18n.description}"/></p>
                <p><fmt:message key="locale.roomtypes.rooms"/> : <c:out value="${roomType.rooms}"/></p>
                <p><fmt:message key="locale.roomtypes.amenities"/> : <c:out
                        value="${roomType.amenitiesToString()}"/></p>
                <p><fmt:message key="locale.roomtypes.totalprice"/> : <c:out value="${roomType.totalPrice}"/> $</p>
                <p>Access: <c:out value="${roomType.access}"/></p>
                <div class="buttons">
                    <form action="${basepath}${Path.ADMIN_ROOMTYPE}/${roomType.id}" method="get">
                        <input type="submit" value="Edit">
                    </form>
                    <form action="${basepath}${Path.ADMIN_ROOMTYPE_I18N}/${roomType.id}" method="get">
                        <input type="submit" value="Translate">
                    </form>
                    <form action="${basepath}${Path.ADMIN_DELETE_ROOMTYPE}/${roomType.id}" method="get">
                        <input type="submit" value="Delete">
                    </form>
                </div>
            </div>
        </div>
    </c:forEach>

</div>
<c:import url="${Path.FOOTER_INCL}"/>
</body>
</html>

