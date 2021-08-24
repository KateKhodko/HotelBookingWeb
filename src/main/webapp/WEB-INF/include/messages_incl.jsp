<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty messages}">
	<ul>
		<c:forEach var="messageKey" items="${requestScope.messages}">
			<li><fmt:message key="${messageKey}" /></li>
		</c:forEach>
	</ul>
</c:if>