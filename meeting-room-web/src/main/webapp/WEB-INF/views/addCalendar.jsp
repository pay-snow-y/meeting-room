<%@ page contentType="text/html; charset=euc-kr" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="application/x-www-form-urlencoded; charset=UTF-8">
		<title>Add Calendar Form</title>
	</head>
	<body>
		<div class="container">
		  <font size="5">일정 추가</font><br />
		  <br />  
		  <div class="input-group date">
			  <form action="/calendar/add/submit" method="POST">
					회의실: <input type="text" name="roomId" /><br /> 
					예약자명: <input type="text" name="regUser" /><br /> 
					예약일자: <input type="text" name="regYmd" value="${regYmd}" /><br/>
					예약시간: <input type="text" size=6 name="startTime" /> ~ <input size=6 type="text" name="endTime" /> <br/>
					반복여부: <input type="radio" name="repeatYn" value="N" checked="checked">아니오 <input type="radio" name="repeatYn" value="Y">예<br/>
					반복횟수: <input type="number" name="repeatCount" value="0" min="0"/><br />
					<br /> 		
				  <input type="submit" value="일정 등록" />				
			  </form>
			  <br/>
			  <form name="return" action="/calendar" method='get'>
			    <input type='submit' value='등록 취소'/>
			  </form>
		  </div>
		</div>
	</body>
</html>