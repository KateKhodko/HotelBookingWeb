<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>
<%@ page import="com.khodko.RoyalHotel.form.RoomTypeFilterForm" %>
<%@ page import="com.khodko.RoyalHotel.form.OrderByForm" %>

<c:import url="${Path.LOCALE_HEADER_INCL}" />

<!DOCTYPE html>
<html>
<head>
    <title>Room Types</title>
    <link rel="stylesheet" href="${basepath}/styles/main.css" type="text/css"/>
</head>
<body>

<c:import url="${Path.LOCALE_MENU_INCL}" />
<c:import url="${Path.LOGOUT_INCL}"/>
<c:import url="${Path.ADMIN_MENU_INCL}"/>

<h1><fmt:message key="locale.roomtypes.title" /></h1>

<p>
	<a href="${basepath}<%=Path.ADMIN_ROOMTYPE%>"><fmt:message key="locale.roomtypes.add" /></a>
<p>

<c:import url="${Path.MESSAGES_INCL}"/>

<div>
	<p>Filters:</p>
	<form action="${requestScope.basepath}${Path.ADMIN_ROOMTYPES}" method="get">		
	    <div>
	        <label for="arrival_date">Dates : </label>
	        <input id="arrival_date" type="date" name="${RoomTypeFilterForm.arrivalDateName}" value="<c:out value='${sessionScope.roomTypeFilterForm.arrivalDateValue}'/>">
	        <label for="depart_date"> - </label>
	        <input id="depart_date" type="date" name="${RoomTypeFilterForm.departDateName}" value="<c:out value='${sessionScope.roomTypeFilterForm.departDateValue}'/>">
	    </div>	    
	    <input type="submit" name="${RoomTypeFilterForm.filterBtnName}" value="Filter"/>
	</form>
	<form action="${requestScope.basepath}${Path.ADMIN_ROOMTYPES}" method="get">
	    <input type="submit" name="${RoomTypeFilterForm.resetBtnName}" value="Reset"/>
	</form>
</div>

<table>
    <tr>
        <th><fmt:message key="locale.roomtypes.image" /></th>
        <th><fmt:message key="locale.roomtypes.name" /></th>
        <th><a href="${requestScope.basepath}${Path.ADMIN_ROOMTYPES}?${OrderByForm.orderByName}=occupancy"><fmt:message key="locale.roomtypes.occupancy"/></a></th>
        <th><fmt:message key="locale.roomtypes.description" /></th>
        <th><a href="${requestScope.basepath}${Path.ADMIN_ROOMTYPES}?${OrderByForm.orderByName}=size"><fmt:message key="locale.roomtypes.size"/></a></th>
        <th><a href="${requestScope.basepath}${Path.ADMIN_ROOMTYPES}?${OrderByForm.orderByName}=rooms"><fmt:message key="locale.roomtypes.rooms"/></a></th>
        <th><a href="${requestScope.basepath}${Path.ADMIN_ROOMTYPES}?${OrderByForm.orderByName}=baseprice"><fmt:message key="locale.roomtypes.baseprice"/></a></th>
        <th><fmt:message key="locale.roomtypes.amenities" /></th>
        <th><fmt:message key="locale.roomtypes.totalprice" /></th>
        <th>Access</th>
        <th><fmt:message key="locale.roomtypes.update" /></th>
        <th>Translate</th>
        <th><fmt:message key="locale.roomtypes.remove" /></th>
    </tr>
    <c:forEach var="roomType" items="${roomTypes}">
        <tr>
            <td>
                <c:if test="${not empty roomType.image}">
                    <img src="${roomType.image}" alt="${roomType.roomTypeI18n.name}" width="50" height="50">
                </c:if>
            </td>
            <td><c:out value="${roomType.roomTypeI18n.name}"/></td>
            <td><c:out value="${roomType.occupancy}"/></td>
            <td><c:out value="${roomType.roomTypeI18n.description}"/></td>
            <td><c:out value="${roomType.size}"/></td>
            <td><c:out value="${roomType.rooms}"/></td>
            <td><c:out value="${roomType.price}"/></td>
            <td><c:out value="${roomType.amenitiesToString()}"/></td>
            <td><c:out value="${roomType.totalPrice}"/></td>
            <td><c:out value="${roomType.access}"/></td>
            <td>
                <form action="${basepath}${Path.ADMIN_ROOMTYPE}/${roomType.id}" method="get">                               
                    <input type="submit" value="Edit">
                </form>
            </td>
            <td>
                <form action="${basepath}${Path.ADMIN_ROOMTYPE_I18N}/${roomType.id}" method="get">                               
                    <input type="submit" value="Translate">
                </form>
            </td>          
            <td>
                <form action="${basepath}${Path.ADMIN_DELETE_ROOMTYPE}/${roomType.id}" method="get">                  
                    <input type="submit" value="Delete">
                </form>
            </td>          
        </tr>
    </c:forEach>
</table>

</body>
</html>

