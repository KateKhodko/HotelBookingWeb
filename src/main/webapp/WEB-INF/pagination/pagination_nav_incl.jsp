<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.khodko.RoyalHotel.pagination.InputForm" %>

<nav aria-label="Pagination">
    <ul class="pagination">

        <c:if test="${requestScope.inputForm.pageValue > 1}">
            <li class="page-item">
                <a class="page-link" href="${requestScope.pagination.url}?${InputForm.LIMIT}=${requestScope.inputForm.limitValue}&${InputForm.PAGE}=1">&laquo;</a>
            </li>
        </c:if>

        <c:if test="${requestScope.inputForm.pageValue > 1}">
            <li class="page-item">
                <a class="page-link" href="${requestScope.pagination.url}?${InputForm.LIMIT}=${requestScope.inputForm.limitValue}&${InputForm.PAGE}=${requestScope.inputForm.pageValue - 1}">&lsaquo;</a>
            </li>
        </c:if>

		<c:if test="${requestScope.pagination.end > requestScope.pagination.begin}">
	        <c:forEach begin="${requestScope.pagination.begin}" end="${requestScope.pagination.end}" var="i">
	            <c:choose>
	                <c:when test="${requestScope.inputForm.pageValue eq i}">
	                    <li class="page-item active">
	                        <a class="page-link">${i} <span class="sr-only">(current)</span></a>
	                    </li>
	                </c:when>
	                <c:otherwise>
	                    <li class="page-item">
	                        <a class="page-link" href="${requestScope.pagination.url}?${InputForm.LIMIT}=${requestScope.inputForm.limitValue}&${InputForm.PAGE}=${i}">${i}</a>
	                    </li>
	                </c:otherwise>
	            </c:choose>
	        </c:forEach>
        </c:if>

        <c:if test="${requestScope.inputForm.pageValue lt requestScope.pagination.pagesCount}">
            <li class="page-item">
                <a class="page-link" href="${requestScope.pagination.url}?${InputForm.LIMIT}=${requestScope.inputForm.limitValue}&${InputForm.PAGE}=${requestScope.inputForm.pageValue + 1}">&rsaquo;</a>
            </li>
        </c:if>

        <c:if test="${requestScope.inputForm.pageValue lt requestScope.pagination.pagesCount}">
            <li class="page-item">
                <a class="page-link" href="${requestScope.pagination.url}?${InputForm.LIMIT}=${requestScope.inputForm.limitValue}&${InputForm.PAGE}=${requestScope.pagination.pagesCount}">&raquo;</a>
            </li>
        </c:if>
    </ul>
</nav>