<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.khodko.RoyalHotel.pagination.InputForm" %>

<form  class="paginationSelect" action="${requestScope.pagination.url}" method="get">
	<input type="hidden" name="${InputForm.PAGE}" value="1">
	<div>
	    <label for="records">Select records per page:</label>
	    <select id="records" name="${InputForm.LIMIT}">
	        <c:forEach var="item" items="${requestScope.pagination.limits}">
	            <option value="<c:out value='${item}'/>"
	                    <c:if test="${requestScope.inputForm.limitValue == item}">
	                        selected
	                    </c:if>>
	                <c:out value='${item}'/></option>
	        </c:forEach>
	    </select>
	    <button type="submit">Submit</button>
	</div>
</form>