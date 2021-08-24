<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>
<%@ page import="com.khodko.RoyalHotel.form.LangForm" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="${Path.LOCALE_HEADER_INCL}" />

<!DOCTYPE html>
<html>
<head>
    <title>Languages</title>
    <link rel="stylesheet" href="${requestScope.basepath}/styles/main1.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/header.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/orderss.css" type="text/css"/>
    <link rel="stylesheet" href="${basepath}/styles/footer1.css" type="text/css"/>
</head>
<body>

<c:import url="${Path.HEADER_INCL}"/>

<img src="${basepath}/images/translate.jfif" class="img">
<div class="overlay">
    <h1>Languages</h1>
</div>

<div class="dateFilter">
    <h2>Add language</h2>
    <div class="formBox">
        <form action="${requestScope.basepath}${Path.ADMIN_SAVE_LANG}" method="post" class="amenityForm">
            <div>
                <label for="input-lang">Locale :</label>
                <input id="input-lang" type="text" name="${LangForm.localeName}" value="<c:out value='${requestScope.langForm.localeValue}'/>">
            </div>
            <div>
                <label for="input-name">Name :</label>
                <input id="input-name" type="text" name="${LangForm.nameName}" value="<c:out value='${requestScope.langForm.nameValue}'/>">
            </div>
            <button type="submit">Add</button>
        </form>
        <c:import url="${Path.MESSAGES_INCL}"/>
    </div>
</div>

<div class="tableBox">
    <table class="amenitiesTable">
        <tr>
            <th>Locale</th>
            <th>Name</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <c:forEach var="lang" items="${requestScope.langs}">
            <tr>
                <form action="${requestScope.basepath}${Path.ADMIN_SAVE_LANG}" method="post">
                    <input type="hidden" name="${LangForm.idName}" value="${lang.id}">
                    <td>
                        <input type="text" name="${LangForm.localeName}" value="<c:out value='${lang.locale}'/>">
                    </td>
                    <td>
                        <input type="text" name="${LangForm.nameName}" value="<c:out value='${lang.name}'/>">
                    </td>
                    <td>
                        <button type="submit">Update</button>
                    </td>
                </form>
                <td>
                    <form action="${requestScope.basepath}${Path.ADMIN_DELETE_LANG}/${lang.id}" method="get">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<c:import url="${Path.FOOTER_INCL}"/>

</body>
</html>

