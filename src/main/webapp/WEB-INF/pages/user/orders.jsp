<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>
<%@ page import="com.khodko.RoyalHotel.form.OrdersFilterForm" %>
<%@ page import="com.khodko.RoyalHotel.form.OrderByForm" %>
<%@ page import="com.khodko.RoyalHotel.pagination.InputForm" %>

<c:import url="${Path.LOCALE_HEADER_INCL}"/>

<html>
<head>
    <title>User orders</title>
    <link rel="stylesheet" href="${basepath}/styles/main1.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/header.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/orderss.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/footer1.css" type="text/css"/>
</head>
<body>

<c:import url="${Path.HEADER_INCL}"/>

<img src="${basepath}/images/profile.jfif" class="img">
<div class="overlay">
    <h1><fmt:message key="locale.user.orders.title"/></h1>
</div>

<div class="dateFilter">
    <h2>Check orders</h2>
    <div class="formBox">
        <form action="${requestScope.pagination.url}" method="get" class="dateForm">
            <input type="hidden" name="${InputForm.LIMIT}" value="${requestScope.inputForm.limitValue}">
            <div>
                <label for="arrival_date_begin">Arrival Date : </label>
                <div class="dateBox">
                    <input id="arrival_date_begin" type="date" name="${OrdersFilterForm.arrivalDateBeginName}"
                           value="<c:out value='${sessionScope.adminOrdersFilterForm.arrivalDateBeginValue}'/>">
                    <label for="arrival_date_end" class="line"> - </label>
                    <input id="arrival_date_end" type="date" name="${OrdersFilterForm.arrivalDateEndName}"
                           value="<c:out value='${sessionScope.adminOrdersFilterForm.arrivalDateEndValue}'/>">
                </div>
            </div>
            <div>
                <label for="depart_date_begin">Depart Date : </label>
                <div class="dateBox">
                    <input id="depart_date_begin" type="date" name="${OrdersFilterForm.departDateBeginName}"
                           value="<c:out value='${sessionScope.adminOrdersFilterForm.departDateBeginValue}'/>">
                    <label for="depart_date_end" class="line"> - </label>
                    <input id="depart_date_end" type="date" name="${OrdersFilterForm.departDateEndName}"
                           value="<c:out value='${sessionScope.adminOrdersFilterForm.departDateEndValue}'/>">
                </div>
            </div>
            <input class="check" type="submit" name="${OrdersFilterForm.filterBtnName}" value="Check"/>
        </form>
        <form action="${requestScope.pagination.url}" method="get">
            <input type="hidden" name="${InputForm.LIMIT}" value="${requestScope.inputForm.limitValue}">
            <input class="reset" type="submit" name="${OrdersFilterForm.resetBtnName}" value="Reset"/>
        </form>
    </div>
</div>

<c:import url="${Path.PAGINATION_SELECT_INCL}"/>

<div class="tableBox">
    <table>
        <tr>
            <th><fmt:message key="locale.user.orders.name"/></th>
            <th>
                <a href="${requestScope.pagination.url}?${InputForm.LIMIT}=${requestScope.inputForm.limitValue}&${OrderByForm.orderByName}=arrivalDate"><fmt:message
                        key="locale.user.orders.arrival"/></a></th>
            <th>
                <a href="${requestScope.pagination.url}?${InputForm.LIMIT}=${requestScope.inputForm.limitValue}&${OrderByForm.orderByName}=departDate"><fmt:message
                        key="locale.user.orders.depart"/></a></th>
            <th><fmt:message key="locale.user.orders.adults"/></th>
            <th><fmt:message key="locale.user.orders.children"/></th>
            <th><fmt:message key="locale.user.orders.totalprice"/></th>
            <th><fmt:message key="locale.user.orders.details"/></th>
        </tr>
        <c:forEach var="order" items="${requestScope.orders}">
            <tr>
                <td><c:out value="${order.roomType.roomTypeI18n.name}"/></td>
                <td><c:out value="${order.arrivalDate}"/></td>
                <td><c:out value="${order.departDate}"/></td>
                <td><c:out value="${order.numAdults}"/></td>
                <td><c:out value="${order.numChildren}"/></td>
                <td><c:out value="${order.price}"/></td>
                <td>
                    <a href="${requestScope.basepath}${Path.USER_ORDER}/${order.id}">Details</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:import url="${Path.PAGINATION_NAV_INCL}"/>
</div>
<c:import url="${Path.FOOTER_INCL}"/>
</body>
</html>
