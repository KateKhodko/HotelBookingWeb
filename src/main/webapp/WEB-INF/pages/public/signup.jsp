<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>
<%@ page import="com.khodko.RoyalHotel.form.SignupForm" %>

<c:import url="${Path.LOCALE_HEADER_INCL}" />

<!DOCTYPE html>
<html>
<head>
    <title>Sign up</title>
    <link rel="stylesheet" href="${basepath}/styles/header.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/form1.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/footer1.css" type="text/css"/>
</head>
<body>

<c:import url="${Path.HEADER_INCL}"/>

<img class="image" src="${basepath}/images/home2.jpeg">

<div class="overlay">
    <div class="dataOverlay">
        <h1><fmt:message key="locale.signup.title" /></h1>
        <c:import url="${Path.MESSAGES_INCL}" />
        <form action="${basepath}${Path.PUBLIC_SIGNUP}" method="post">
            <div>
                <label for="inputName"><fmt:message key="locale.signup.name" /></label>
                <input id="inputName" type="text" placeholder="Name"
                       name="${SignupForm.usernameName}" value="<c:out value='${signupForm.usernameValue}'/>" autofocus>
            </div>
            <div>
                <label for="inputEmail"><fmt:message key="locale.signup.email" /></label>
                <input id="inputEmail" type="email" placeholder="Email address"
                       name="${SignupForm.emailName}" value="<c:out value='${signupForm.emailValue}'/>" >
            </div>
            <div>
                <label for="inputPassword"><fmt:message key="locale.signup.password" /></label>
                <input id="inputPassword" type="password" name="${SignupForm.passwordName}" value="" placeholder="Password"/>
            </div>
            <div>
                <label for="inputPasswordRepeat"><fmt:message key="locale.signup.repeat" /></label>
                <input id="inputPasswordRepeat" type="password" name="${SignupForm.passwordRepeatName}" value="" placeholder="Repeat password">
            </div>

            <button type="submit" class="registerButton"><fmt:message key="locale.signup.signup" /></button>
        </form>
    </div>
    <c:import url="${Path.FOOTER_INCL}"/>
</div>

</html>