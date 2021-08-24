<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>
<%@ page import="com.khodko.RoyalHotel.form.AmenityForm" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="${Path.LOCALE_HEADER_INCL}" />

<!DOCTYPE html>
<html>
<head>
    <title>Amenities</title>
    <link rel="stylesheet" href="${requestScope.basepath}/styles/main.css" type="text/css"/>
</head>
<body>

<c:import url="${Path.LOCALE_MENU_INCL}" />
<c:import url="${Path.LOGOUT_INCL}"/>
<c:import url="${Path.ADMIN_MENU_INCL}"/>

<h1><fmt:message key="locale.amenities.title" /></h1>

<form action="${requestScope.basepath}${Path.ADMIN_SAVE_AMENITY}" method="post">
    <div>
        <label for="amenity"><fmt:message key="locale.amenities.amenity" />:</label>
        <input id="amenity" type="text" name="${AmenityForm.nameName}" value="<c:out value='${requestScope.amenityForm.nameValue}'/>">
    </div>
    <div>
        <label for="price"><fmt:message key="locale.amenities.price" />:</label>
        <input id="price" type="text" name="${AmenityForm.priceName}" value="<c:out value='${requestScope.amenityForm.priceValue}'/>">
    </div>
    <button type="submit"><fmt:message key="locale.amenities.add" /></button>
</form>

<c:import url="${Path.MESSAGES_INCL}"/>

<table>
    <tr>
        <th><fmt:message key="locale.amenities.name" /></th>
        <th><fmt:message key="locale.amenities.price" /></th>
        <th><fmt:message key="locale.amenities.update" /></th>
        <th>Translate</th>
        <th><fmt:message key="locale.amenities.remove" /></th>        
    </tr>
    <c:forEach var="amenity" items="${requestScope.amenities}">
        <tr>
            <form action="${requestScope.basepath}${Path.ADMIN_SAVE_AMENITY}" method="post">
                <input type="hidden" name="${AmenityForm.idName}" value="${amenity.id}"> 
                <input type="hidden" name="${AmenityForm.nameName}" value="exist">                
                <td>
                    <c:out value='${amenity.amenityI18n.name}'/>
                </td>
                <td>
                    <input type="text" name="${AmenityForm.priceName}" value="<c:out value='${amenity.price}'/>">
                </td>
                <td>
                    <button type="submit"><fmt:message key="locale.amenities.update" /></button>
                </td>
            </form>
            <td>
                <form action="${requestScope.basepath}${Path.ADMIN_AMENITY_I18N}/${amenity.id}" method="get">                    
                    <button type="submit">Translate</button>
                </form>
            </td>
            <td>
                <form action="${requestScope.basepath}${Path.ADMIN_DELETE_AMENITY}/${amenity.id}" method="get">                    
                    <button type="submit"><fmt:message key="locale.amenities.delete" /></button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>

