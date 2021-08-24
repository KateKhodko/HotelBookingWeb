<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>

<c:import url="${Path.LOCALE_HEADER_INCL}"/>

<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="${basepath}/styles/main1.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/header.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/home1.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/footer1.css" type="text/css"/>
    <link href="${basepath}/font-awesome/css/fontawesome.min.css" rel="stylesheet">
    <link href="${basepath}/font-awesome/css/all.min.css" rel="stylesheet">
</head>
<body>
<header>
    <c:import url="${Path.HEADER_INCL}"/>
    <div class="overlay">
        <h1><fmt:message key="locale.home.hotelName"/></h1>
    </div>
    <video src="https://vod-progressive.akamaized.net/exp=1628558534~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F930%2F14%2F354651355%2F1443116478.mp4~hmac=1f2b3b07cbf399d426a215d48d871ea99b02f1e0a56c7dc9b25aa6a6b0b6da34/vimeo-prod-skyfire-std-us/01/930/14/354651355/1443116478.mp4?filename=Hotel+-+25907.mp4"
           autoplay muted loop class="video">
    </video>
</header>
<div class="bottom">
    <div class="info">
        <h2><fmt:message key="locale.home.hotelFacilities"/></h2>
        <div class="infoBox">
            <div class="facility">
                <h3>
                    <img src="https://image.flaticon.com/icons/png/512/1683/1683832.png"><br>
                    <fmt:message key="locale.home.restaurant"/>
                </h3>
                <p>
                    <fmt:message key="locale.home.restorauntpar"/>
                </p>
            </div>
            <div class="facility">
                <h3>
                    <img src="https://image.flaticon.com/icons/png/512/3069/3069530.png"><br>
                    <fmt:message key="locale.home.bar"/>
                </h3>
                <p>
                    <fmt:message key="locale.home.barpar"/>
                </p>
            </div>
            <div class="facility">
                <h3>
                    <img src="${basepath}/images/3069493.png"><br>
                    <fmt:message key="locale.home.freewifi"/>
                </h3>
                <p>
                    <fmt:message key="locale.home.wifipar"/>
                </p>
            </div>
        </div>
    </div>
    <c:import url="${Path.FOOTER_INCL}"/>
</div>
</body>
</html>