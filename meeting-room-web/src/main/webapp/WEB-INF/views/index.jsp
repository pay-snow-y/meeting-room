<%@ page contentType="text/html; charset=euc-kr" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>

<ul>
	<c:forEach var="calendar" items="${calendars}">
    	<li>${calendar}</li>
    </c:forEach>
</ul>

</body>
</html>
