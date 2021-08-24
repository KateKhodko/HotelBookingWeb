<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.khodko.RoyalHotel.config.Path" %>


<c:if test="${not empty requestScope.i18ns}">
	<ul class="languages">
		<c:forEach var="i18n" items="${requestScope.i18ns}">
			<li><a href="?lang=${i18n.id}"><c:out value='${i18n.name}'/></a></li>
		</c:forEach>
	</ul>
</c:if>