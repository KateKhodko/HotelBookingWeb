<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>
<%@ page import="com.khodko.RoyalHotel.form.ProfileForm" %>
<%@ page import="com.khodko.RoyalHotel.config.CardType" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="${Path.LOCALE_HEADER_INCL}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <link rel="stylesheet" href="${basepath}/styles/header.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/profile1.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/footer1.css" type="text/css"/>
</head>
<body>

<c:import url="${Path.HEADER_INCL}"/>

<img class="image" src="${basepath}/images/bg.jpeg">
<div class="overlay">
    <div class="dataOverlay">
        <h1><fmt:message key="locale.profile.title" /></h1>

        <c:import url="${Path.MESSAGES_INCL}" />
        <form action="${requestScope.basepath}${Path.USER_SAVE_PROFILE}" method="post">

            <div class="basicInfo">
                <h2>Basic info</h2>
                <div>
                    <input type="hidden" name="${ProfileForm.userIdName}" value="<c:out value='${requestScope.profileForm.userIdValue}'/>">
                    <div>
                        <p class="email"><span><fmt:message key="locale.profile.email" /></span>: <c:out value='${requestScope.authUser.email}'/></p>
                    </div>
                    <div>
                        <label for="username"><fmt:message key="locale.profile.username" />: </label>
                        <input id="username" type="text" name="${ProfileForm.usernameName}" value="<c:out value='${requestScope.profileForm.usernameValue}'/>">
                    </div>
                    <div>
                        <label for="name"><fmt:message key="locale.profile.firstname" />: </label>
                        <input id="name" type="text" name="${ProfileForm.firstNameName}" value="<c:out value='${requestScope.profileForm.firstNameValue}'/>">
                    </div>
                    <div>
                        <label for="surname"><fmt:message key="locale.profile.lastname" />: </label>
                        <input id="surname" type="text" name="${ProfileForm.lastNameName}" value="<c:out value='${requestScope.profileForm.lastNameValue}'/>">
                    </div>
                    <div>
                        <label for="country"><fmt:message key="locale.profile.country" />: </label>
                        <input id="country" type="text" name="${ProfileForm.countryName}" value="<c:out value='${requestScope.profileForm.countryValue}'/>">
                    </div>
                </div>
            </div>

            <div class="editPassword">
                <h2>Edit password</h2>
                <div>
                    <div>
                        <label for="newPassword"><fmt:message key="locale.profile.newpassword" />: </label>
                        <input id="newPassword" type="password" name="${ProfileForm.newPasswordName}" value="">
                    </div>
                    <div>
                        <label for="repeatNewPassword"><fmt:message key="locale.profile.repeatpassword" />: </label>
                        <input id="repeatNewPassword" type="password" name="${ProfileForm.repeatNewPasswordName}" value="">
                    </div>
                    <div>
                        <label for="oldPassword"><fmt:message key="locale.profile.oldpassword" />: </label>
                        <input id="oldPassword" type="password" name="${ProfileForm.oldPasswordName}" value="">
                    </div>
                </div>
            </div>

            <div class="cardInfo">
                <h2>Card info</h2>
                <div>
                    <div>
                        <label for="type"><fmt:message key="locale.profile.cardtype" />: </label>
                        <select id="type" name="${ProfileForm.cardTypeName}" size="1">
                            <c:forEach var="cardType" items="${CardType.CARD_TYPES}">
                                <option value="<c:out value='${cardType}'/>"
                                        <c:if test="${requestScope.profileForm.cardTypeValue == cardType}">
                                            selected
                                        </c:if>>
                                    <c:out value='${cardType}'/></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <label for="card"><fmt:message key="locale.profile.cardnumber" />: </label>
                        <input id="card" type="text" size="20" name="${ProfileForm.cardNumberName}" value="<c:out value='${requestScope.profileForm.cardNumberValue}'/>">
                    </div>
                </div>
            </div>

            <button class="profileButton" type="submit"><fmt:message key="locale.profile.save" /></button>
        </form>

        <c:if test="${not empty requestScope.infoMessage}">
            <p><fmt:message key="${requestScope.infoMessage}" /></p>
        </c:if>
    </div>
    <c:import url="${Path.FOOTER_INCL}"/>
</div>
</body>
</html>