<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>
<%@ page import="com.khodko.RoyalHotel.form.RoomTypeForm" %>
<%@ page import="com.khodko.RoyalHotel.form.AmenityCheckBoxForm" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="${Path.LOCALE_HEADER_INCL}" />

<!DOCTYPE html>
<html>
<head>
    <title>Create Room Type</title>
    <link rel="stylesheet" href="${basepath}/styles/main.css" type="text/css"/>
</head>
<body>

<c:import url="${Path.LOCALE_MENU_INCL}" />
<c:import url="${Path.LOGOUT_INCL}"/>
<c:import url="${Path.ADMIN_MENU_INCL}"/>

<h1><fmt:message key="locale.addroomtype.title" /></h1>

<c:if test="${not empty roomType.image}">
    <img src="${roomType.image}" width="250">
</c:if>
<br>

<c:import url="${Path.MESSAGES_INCL}"/>

<form action="${basepath}${Path.ADMIN_SAVE_ROOMTYPE}" method="post">
	<input type="hidden" name="${RoomTypeForm.idName}" value="<c:out value='${requestScope.roomTypeForm.idValue}'/>" >
	<input type="hidden" name="${RoomTypeForm.roomTypeI18nIdName}" value="<c:out value='${requestScope.roomTypeForm.roomTypeI18nIdValue}'/>" >
	<div>    	
    	<label for="name"><fmt:message key="locale.addroomtype.name" />:</label>
    	<input id="name" type="text" name="${RoomTypeForm.nameName}" value="<c:out value='${requestScope.roomTypeForm.nameValue}'/>" >
    </div>
    <div>	    
	    <label for="occupancy"><fmt:message key="locale.addroomtype.occupancy" />:</label>
	    <input id="occupancy" type="text" name="${RoomTypeForm.occupancyName}" value="<c:out value='${requestScope.roomTypeForm.occupancyValue}'/>" >
	</div>    
	<div>	    
	    <label for="description"><fmt:message key="locale.addroomtype.description" />:</label>
	    <input id="description" type="text" name="${RoomTypeForm.descriptionName}" value="<c:out value='${requestScope.roomTypeForm.descriptionValue}'/>" >
	</div>    
	<div>    	    
	    <label for="imagelink"><fmt:message key="locale.addroomtype.image" />:</label>
	    <input id="imagelink" type="text" name="${RoomTypeForm.imageName}" value="<c:out value='${requestScope.roomTypeForm.imageValue}'/>" >
	</div>    
	<div>    	    
	    <label for="size"><fmt:message key="locale.addroomtype.size" />:</label>
	    <input id="size" type="text" name="${RoomTypeForm.sizeName}" value="<c:out value='${requestScope.roomTypeForm.sizeValue}'/>" >
	</div>    
	<div>    	    
	    <label for="price"><fmt:message key="locale.addroomtype.price" />:</label>
	    <input id="price" type="text" name="${RoomTypeForm.priceName}" value="<c:out value='${requestScope.roomTypeForm.priceValue}'/>" >
	</div>    
	<div>     	    
	    <label for="rooms"><fmt:message key="locale.addroomtype.rooms" />:</label>
	    <input id="rooms" type="text" name="${RoomTypeForm.roomsName}" value="<c:out value='${requestScope.roomTypeForm.roomsValue}'/>" >
	</div>    
	<div> 
	    <input id="access" type="checkbox" name="${RoomTypeForm.accessName}" 
	    	<c:if test="${requestScope.roomTypeForm.accessValue}">
                    checked
            </c:if>>	    
	    <label for="access">Access </label>	    
	</div> 	
	<div>    	    
	    <label><fmt:message key="locale.addroomtype.amenities" />:</label>
	    <c:forEach var="amenityBox" items="${amenityBoxes}">
	        <div>
	            <input type="checkbox" id="${amenityBox.entity.amenityI18n.name}" ${amenityBox.checked} 
	            	name="${AmenityCheckBoxForm.amenitiesName}" value="${amenityBox.entity.id}">
	            <label for="${amenityBox.entity.amenityI18n.name}"><c:out value="${amenityBox.entity.amenityI18n.name}"/></label>
	        </div>
	    </c:forEach>
	</div>
	<button type="submit">Save</button>	
</form>
</body>
</html>

