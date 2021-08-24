<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>
<%@ page import="com.khodko.RoyalHotel.form.PaymentForm" %>
<%@ page import="com.khodko.RoyalHotel.config.CardType" %>


<c:import url="${Path.LOCALE_HEADER_INCL}"/>

<html>
<head>
    <title>Payment</title>
    <link rel="stylesheet" href="${requestScope.basepath}/styles/main1.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/header.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/payment.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/footer1.css" type="text/css"/>
</head>
<body>

<c:import url="${Path.HEADER_INCL}"/>

<img class="image" src="${basepath}/images/payment.jpeg">
<div class="overlay">
    <h1><fmt:message key="locale.payment.title"/></h1>
</div>


<div class="dataOverlay">


    <form action="${requestScope.basepath}${Path.USER_CONFIRM_PAYMENT}" method="post">
        <div class="basicInfo">
            <h2>Basic info</h2>
            <div>
                <div>
                    <p><span><fmt:message key="locale.payment.name"/></span>: <c:out
                            value='${requestScope.roomType.roomTypeI18n.name}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.payment.arrival"/></span>: <c:out
                            value='${sessionScope.sessionBooking.dateValue}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.payment.days"/></span>: <c:out
                            value='${sessionScope.sessionBooking.daysValue}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.payment.adults"/></span>: <c:out
                            value='${sessionScope.sessionBooking.adultsValue}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.payment.children"/></span>: <c:out
                            value='${sessionScope.sessionBooking.childrenValue}'/></p>
                </div>
                <div>
                    <p><span><fmt:message key="locale.payment.email"/></span>: <c:out
                            value='${requestScope.authUser.email}'/></p>
                </div>

                <input type="hidden" name="${PaymentForm.userIdName}"
                       value="<c:out value='${requestScope.paymentForm.userIdValue}'/>">
                <div>
                    <label for="name"><fmt:message key="locale.payment.firstname"/>: </label>
                    <input id="name" type="text" name="${PaymentForm.firstNameName}"
                           value="<c:out value='${requestScope.paymentForm.firstNameValue}'/>">
                </div>
                <div>
                    <label for="surname"><fmt:message key="locale.payment.lastname"/>: </label>
                    <input id="surname" type="text" name="${PaymentForm.lastNameName}"
                           value="<c:out value='${requestScope.paymentForm.lastNameValue}'/>">
                </div>
                <div>
                    <label for="country"><fmt:message key="locale.payment.country"/>: </label>
                    <input id="country" type="text" name="${PaymentForm.countryName}"
                           value="<c:out value='${requestScope.paymentForm.countryValue}'/>">
                </div>
            </div>
        </div>

        <div class="cardInfo">
            <h2>Card info</h2>
            <div>
                <div>
                    <label for="type"><fmt:message key="locale.payment.cardtype"/>: </label>
                    <select id="type" name="${PaymentForm.cardTypeName}" size="1">
                        <c:forEach var="cardType" items="${CardType.CARD_TYPES}">
                            <option value="<c:out value='${cardType}'/>"
                                    <c:if test="${requestScope.paymentForm.cardTypeValue == cardType}">
                                        selected
                                    </c:if>>
                                <c:out value='${cardType}'/></option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <label for="card"><fmt:message key="locale.payment.cardnumber"/>: </label>
                    <input id="card" type="text" size="20" name="${PaymentForm.cardNumberName}"
                           value="<c:out value='${requestScope.paymentForm.cardNumberValue}'/>">
                </div>
                <div>
                    <label for="secureCode"><fmt:message key="locale.payment.securecode"/>: </label>
                    <input id="secureCode" type="text" size="3" name="${PaymentForm.secureCodeName}"
                           value="<c:out value='${requestScope.paymentForm.secureCodeValue}'/>">
                </div>
                <div class="dateBox">
                    <p class="expDate"><fmt:message key="locale.payment.expirationdate"/>: </p>
                    <div class="formBox">
                        <div>
                            <label for="month"><fmt:message key="locale.payment.month"/>: </label>
                            <input id="month" type="number" size="2" min="1" max="12" name="${PaymentForm.expirationMonthName}"
                                   value="<c:out value='${requestScope.paymentForm.expirationMonthValue}'/>">
                        </div>
                        <div>
                            <label for="year"><fmt:message key="locale.payment.year"/>: </label>
                            <input id="year" type="number" size="4" min="${requestScope.yearNow}"
                                   name="${PaymentForm.expirationYearName}"
                                   value="<c:out value='${requestScope.paymentForm.expirationYearValue}'/>">
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="totalPrice">
            <h2><fmt:message key="locale.payment.totalprice"/></h2>
            <div>
                <p><c:out value='${requestScope.totalPrice * sessionScope.sessionBooking.daysValue}'/> $</p>
            </div>
        </div>
        <c:import url="${Path.MESSAGES_INCL}"/>

        <button type="submit" class="profileButton"><fmt:message key="locale.payment.order"/></button>
    </form>
</div>
<c:import url="${Path.FOOTER_INCL}"/>
</body>
</html>
