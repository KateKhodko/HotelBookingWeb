<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>
<%@ page import="com.khodko.RoyalHotel.form.LoginForm" %>

<c:import url="${Path.LOCALE_HEADER_INCL}" />

<!DOCTYPE html>
<html>
<head>
    <title>Log in</title>
</head>
<body>

<c:import url="${Path.LOCALE_MENU_INCL}" />

<h1><fmt:message key="locale.login.title" /></h1>

<c:import url="${Path.MESSAGES_INCL}" />

<form action="${requestScope.basepath}${Path.PUBLIC_LOGIN}" method="post">
    <div>
	    <label for="inputEmail"><fmt:message key="locale.login.email" /></label>
	    <input id="inputEmail" type="email" placeholder="Email address"
	    	    name="${LoginForm.emailName}" value="<c:out value='${requestScope.loginForm.emailValue}'/>" >
    </div>
    <div>
	    <label for="inputPassword"><fmt:message key="locale.login.password" /></label>
	    <input id="inputPassword" type="password" placeholder="Password"
               name="${LoginForm.passwordName}" value="" />
    </div>    
    <div>
    	<input id="inputRememberMe" type="checkbox" name="${LoginForm.rememberMeName}" ${requestScope.loginForm.rememberMeValue}/>
    	<label for="inputRememberMe"><fmt:message key="locale.login.remember" /></label>
	</div>
    <br/>
    <button type="submit"><fmt:message key="locale.login.login" /></button>
</form>

<p><a href="${requestScope.basepath}${Path.PUBLIC_SHOW_SIGNUP}"><fmt:message key="locale.login.signup" /></a></p>

</html>