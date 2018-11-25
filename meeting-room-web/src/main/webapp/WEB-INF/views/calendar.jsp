<%@ page contentType="text/html; charset=euc-kr" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<body>
		<div style="width: 1000px;>
			<span style="float:left">
				<button type="button" onclick="location.href='/calendar/add?regYmd=${regYmd}'">일정 추가</button>
			</span>
			<br/><br/>
			<span style="float:left">
				<form action="/calendar" method="GET">
					날짜: <input type="text" name="regYmd" value="${regYmd}"/>
					<input type="submit" value="날짜이동" />
				</form>
			</span>
		</div>
		<br/><br/><br/>
		
		<table border="1" cellpadding="20" align="left">
			<tr>
				<c:forEach var="room" items="${rooms}">
		    		<td>${room.roomName}</td>
		    	</c:forEach>
			</tr>
			<tr>
				<c:forEach var="room" items="${rooms}">
					<td>
						<ul>
							<c:forEach var="calendar" items="${calendars}">
								<c:if test="${room.roomId eq calendar.roomId}">
						    		<li>${calendar}</li>
						    	</c:if>
						    </c:forEach>
						</ul>
					</td>
				</c:forEach>
			</tr>
		</table>
	
	</body>
	
	<c:if test="${!empty result}">
		<script>alert('${result}');</script>
	</c:if>
</html>
