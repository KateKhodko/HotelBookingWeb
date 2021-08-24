<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>
<%@ page import="com.khodko.RoyalHotel.form.RoomTypeForm" %>
<%@ page import="com.khodko.RoyalHotel.form.AmenityCheckBoxForm" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="${Path.LOCALE_HEADER_INCL}"/>

<!DOCTYPE html>
<html>
<head>
    <title>Create Room Type</title>
    <link rel="stylesheet" href="${basepath}/styles/main1.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/header.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/roomtypeDetails1.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/footer1.css" type="text/css"/>
</head>
<body>

<c:import url="${Path.HEADER_INCL}"/>

<img src="${basepath}/images/roomtype.jpeg" class="img">
<div class="overlay">
    <h1><fmt:message key="locale.details.title"/></h1>
</div>

<div class="detailsBox">
    <form action="${basepath}${Path.ADMIN_SAVE_ROOMTYPE}" method="post">
        <div class="room">
            <div class="image">
                <c:if test="${not empty requestScope.roomTypeForm.imageValue}">
                    <img src="${requestScope.roomTypeForm.imageValue}" width="250">
                </c:if>
            </div>
            <div class="details">
                <input type="hidden" name="${RoomTypeForm.idName}"
                       value="<c:out value='${requestScope.roomTypeForm.idValue}'/>">
                <input type="hidden" name="${RoomTypeForm.roomTypeI18nIdName}"
                       value="<c:out value='${requestScope.roomTypeForm.roomTypeI18nIdValue}'/>">
                <div>
                    <label for="name"><fmt:message key="locale.addroomtype.name"/>:</label>
                    <input id="name" type="text" name="${RoomTypeForm.nameName}"
                           value="<c:out value='${requestScope.roomTypeForm.nameValue}'/>">
                </div>
                <div>
                    <label for="occupancy"><fmt:message key="locale.addroomtype.occupancy"/>:</label>
                    <input id="occupancy" type="text" name="${RoomTypeForm.occupancyName}"
                           value="<c:out value='${requestScope.roomTypeForm.occupancyValue}'/>">
                </div>
                <div>
                    <label for="description"><fmt:message key="locale.addroomtype.description"/>:</label>
                    <input id="description" type="text" name="${RoomTypeForm.descriptionName}"
                           value="<c:out value='${requestScope.roomTypeForm.descriptionValue}'/>">
                </div>
                <div>
                    <label for="imagelink"><fmt:message key="locale.addroomtype.image"/>:</label>
                    <input id="imagelink" type="text" name="${RoomTypeForm.imageName}"
                           value="<c:out value='${requestScope.roomTypeForm.imageValue}'/>">
                </div>
                <div>
                    <label for="size"><fmt:message key="locale.addroomtype.size"/>:</label>
                    <input id="size" type="text" name="${RoomTypeForm.sizeName}"
                           value="<c:out value='${requestScope.roomTypeForm.sizeValue}'/>">
                </div>
                <div>
                    <label for="rooms"><fmt:message key="locale.addroomtype.rooms"/>:</label>
                    <input id="rooms" type="text" name="${RoomTypeForm.roomsName}"
                           value="<c:out value='${requestScope.roomTypeForm.roomsValue}'/>">
                </div>
                <div>
                    <label for="price"><fmt:message key="locale.addroomtype.price"/>:</label>
                    <input id="price" type="text" name="${RoomTypeForm.priceName}"
                           value="<c:out value='${requestScope.roomTypeForm.priceValue}'/>">
                </div>
                <div class="rememberBox">
                    <input id="access" class="checkbox" type="checkbox" name="${RoomTypeForm.accessName}"
                    <c:if test="${requestScope.roomTypeForm.accessValue}">
                           checked
                    </c:if>>
                    <label class="rememberMe" for="access">Access </label>
                </div>
            </div>
        </div>

        <div class="amenities amenitiesBox">
            <h3><fmt:message key="locale.addroomtype.amenities"/>:</h3>
            <c:forEach var="amenityBox" items="${amenityBoxes}">
                <div class="rememberBox">
                    <input type="checkbox" class="checkbox" id="${amenityBox.entity.amenityI18n.name}" ${amenityBox.checked}
                           name="${AmenityCheckBoxForm.amenitiesName}" value="${amenityBox.entity.id}">
                    <label class="rememberMe" for="${amenityBox.entity.amenityI18n.name}"><c:out
                            value="${amenityBox.entity.amenityI18n.name}"/></label>
                </div>
            </c:forEach>
        </div>
        <c:import url="${Path.MESSAGES_INCL}"/>
        <div class="saveBox">
            <button type="submit">Save</button>
        </div>
    </form>
</div>
<c:import url="${Path.FOOTER_INCL}"/>

</body>
</html>

