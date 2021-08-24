<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>
<%@ page import="com.khodko.RoyalHotel.form.AmenityI18nForm" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="${Path.LOCALE_HEADER_INCL}" />

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

<img class="image" src="${basepath}/images/amenities2.jfif">
<div class="overlay">
	<div class="dataOverlay">
		<h1>Localization</h1>
		<div class="amenityOverlay">
			<p class="defaultName"><span>Default (england)</span>: <c:out value="${requestScope.amenityEn.name}"/></p>
			<form action="${requestScope.basepath}${Path.ADMIN_AMENITY_I18N}/${amenityI18nForm.amenityIdValue}" method="get">
				<div>
					<label for="i18n-select">Select language: </label>
					<select id="i18n-select" name="${AmenityI18nForm.i18nIdName}" size="1" onchange="this.form.submit()">
						<c:forEach var="i18n" items="${requestScope.i18ns}">
							<option value="<c:out value='${i18n.id}'/>"
									<c:if test="${amenityI18nForm.i18nIdValue == i18n.id}">selected</c:if>>
								<c:out value='${i18n.name}'/>
							</option>
						</c:forEach>
					</select>
				</div>
			</form>

			<c:import url="${Path.MESSAGES_INCL}"/>

			<form action="${requestScope.basepath}${Path.ADMIN_SAVE_AMENITY_I18N}" method="post">
				<input type="hidden" name="${AmenityI18nForm.idName}" value="${requestScope.amenityI18nForm.idValue}">
				<input type="hidden" name="${AmenityI18nForm.amenityIdName}" value="${requestScope.amenityI18nForm.amenityIdValue}">
				<input type="hidden" name="${AmenityI18nForm.i18nIdName}" value="${requestScope.amenityI18nForm.i18nIdValue}">
				<div>
					<label for="i18n-name"><fmt:message key="locale.amenities.amenity" />: </label>
					<input id="i18n-name" type="text" name="${AmenityI18nForm.nameName}" value="<c:out value='${requestScope.amenityI18nForm.nameValue}'/>">
				</div>
				<button type="submit">Save</button>
			</form>

			<p><c:out value="${requestScope.save_message}" /></p>
		</div>
	</div>
	<c:import url="${Path.FOOTER_INCL}"/>
</div>


</body>
</html>

