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
    <title>Orders</title>
    <link rel="stylesheet" href="${requestScope.basepath}/styles/main1.css" type="text/css"/>
    <link rel="stylesheet" href="${requestScope.basepath}/styles/header.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/orderss.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/footer1.css" type="text/css"/>
</head>
<body>

<c:import url="${Path.HEADER_INCL}"/>

<img src="${basepath}/images/profile.jfif" class="img">
<div class="overlay">
    <h1><fmt:message key="locale.orders.title"/></h1>
</div>



<div class="dateFilter">
    <h2>Check orders</h2>
    <div class="formBox">
        <form class="dateForm" action="${requestScope.pagination.url}" method="get">
            <input type="hidden" name="${InputForm.LIMIT}" value="${requestScope.inputForm.limitValue}">
            <div>
                <label for="arrival_date_begin">Arrival Date : </label>
                <div class="dateBox">
                    <input id="arrival_date_begin" type="date" name="${OrdersFilterForm.arrivalDateBeginName}"
                           value="<c:out value='${sessionScope.adminOrdersFilterForm.arrivalDateBeginValue}'/>">
                    <label class="line" for="arrival_date_end"> - </label>
                    <input id="arrival_date_end" type="date" name="${OrdersFilterForm.arrivalDateEndName}"
                           value="<c:out value='${sessionScope.adminOrdersFilterForm.arrivalDateEndValue}'/>">
                </div>
            </div>
            <div>
                <label for="depart_date_begin">Depart Date : </label>
                <div class="dateBox">
                    <input id="depart_date_begin" type="date" name="${OrdersFilterForm.departDateBeginName}"
                           value="<c:out value='${sessionScope.adminOrdersFilterForm.departDateBeginValue}'/>">
                    <label class="line" for="depart_date_end"> - </label>
                    <input id="depart_date_end" type="date" name="${OrdersFilterForm.departDateEndName}"
                           value="<c:out value='${sessionScope.adminOrdersFilterForm.departDateEndValue}'/>">
                </div>
            </div>
            <input class="check" type="submit" name="${OrdersFilterForm.filterBtnName}" value="Filter"/>
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
            <th><fmt:message key="locale.orders.roomtype"/></th>
            <th>
                <a href="${requestScope.pagination.url}?${InputForm.LIMIT}=${requestScope.inputForm.limitValue}&${OrderByForm.orderByName}=arrivalDate"><fmt:message
                        key="locale.orders.arrival"/></a></th>
            <th>
                <a href="${requestScope.pagination.url}?${InputForm.LIMIT}=${requestScope.inputForm.limitValue}&${OrderByForm.orderByName}=departDate"><fmt:message
                        key="locale.orders.depart"/></a></th>
            <th><fmt:message key="locale.orders.email"/></th>
            <th><fmt:message key="locale.orders.firstname"/></th>
            <th><fmt:message key="locale.orders.lastname"/></th>
            <th><fmt:message key="locale.orders.totalprice"/></th>
            <th><fmt:message key="locale.orders.details"/></th>
            <th>Delete</th>
        </tr>
        <c:forEach var="order" items="${requestScope.orders}">
            <tr>
                <td><c:out value="${order.roomType.roomTypeI18n.name}"/></td>
                <td><c:out value="${order.arrivalDate}"/></td>
                <td><c:out value="${order.departDate}"/></td>
                <td><c:out value="${order.user.email}"/></td>
                <td><c:out value="${order.user.firstName}"/></td>
                <td><c:out value="${order.user.lastName}"/></td>
                <td><c:out value="${order.price}"/></td>
                <td>
                    <a href="${requestScope.basepath}${Path.ADMIN_ORDER}/${order.id}">Details</a>
                </td>
                <td>
                    <form action="${basepath}${Path.ADMIN_DELETE_ORDER}/${order.id}" method="get">
                        <button class="delete" type="submit" value="Delete">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <c:import url="${Path.PAGINATION_NAV_INCL}"/>
</div>
<c:import url="${Path.FOOTER_INCL}"/>
</body>
</html>
