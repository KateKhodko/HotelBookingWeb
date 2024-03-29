<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>
<%@ page import="com.khodko.RoyalHotel.form.RoomTypeI18nForm" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="${Path.LOCALE_HEADER_INCL}"/>

<!DOCTYPE html>
<html>
<head>
    <title>Amenity</title>
    <link rel="stylesheet" href="${requestScope.basepath}/styles/main1.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/header.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/form1.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/footer1.css" type="text/css"/>
</head>
<body>

<c:import url="${Path.HEADER_INCL}"/>

<img class="image" src="${basepath}/images/roomtype.jpeg">
<div class="overlay">
    <div class="dataOverlay">
        <h1>Localization</h1>
        <div class="amenityOverlay">
            <div>
                <h3 class="defaultName"><span>Default localization (england):</span></h3>
                <p class="defaultName"><span>Name</span> : <c:out value="${requestScope.roomTypeEn.name}"/></p>
                <p class="defaultName"><span>Description</span> : <c:out value="${requestScope.roomTypeEn.description}"/></p>
            </div>
            <div class="formBox">
                <form action="${requestScope.basepath}${Path.ADMIN_ROOMTYPE_I18N}/${roomTypeI18nForm.roomTypeIdValue}"
                      method="get">
                    <div>
                        <label for="i18n-select">Select language: </label>
                        <select id="i18n-select" name="${RoomTypeI18nForm.i18nIdName}" size="1"
                                onchange="this.form.submit()">
                            <c:forEach var="i18n" items="${requestScope.i18ns}">
                                <option value="<c:out value='${i18n.id}'/>"
                                        <c:if test="${roomTypeI18nForm.i18nIdValue == i18n.id}">selected</c:if>>
                                    <c:out value='${i18n.name}'/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </form>

                <c:import url="${Path.MESSAGES_INCL}"/>

                <form action="${requestScope.basepath}${Path.ADMIN_SAVE_ROOMTYPE_I18N}" method="post">
                    <input type="hidden" name="${RoomTypeI18nForm.idName}" value="${requestScope.roomTypeI18nForm.idValue}">
                    <input type="hidden" name="${RoomTypeI18nForm.roomTypeIdName}"
                           value="${requestScope.roomTypeI18nForm.roomTypeIdValue}">
                    <input type="hidden" name="${RoomTypeI18nForm.i18nIdName}"
                           value="${requestScope.roomTypeI18nForm.i18nIdValue}">
                    <div>
                        <label for="i18n-name">Name : </label>
                        <input id="i18n-name" type="text" name="${RoomTypeI18nForm.nameName}"
                               value="<c:out value='${requestScope.roomTypeI18nForm.nameValue}'/>">
                    </div>
                    <div>
                        <label for="i18n-description">Description : </label>
                        <input id="i18n-description" type="text" name="${RoomTypeI18nForm.descriptionName}"
                               value="<c:out value='${requestScope.roomTypeI18nForm.descriptionValue}'/>">
                    </div>
                    <button type="submit">Save</button>
                </form>

                <p><c:out value="${requestScope.save_message}"/></p>
            </div>
        </div>
    </div>
    <c:import url="${Path.FOOTER_INCL}"/>
</div>


</body>
</html>

