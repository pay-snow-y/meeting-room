<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Calendar Form</title>
</head>
<body>

  <font size="5">Add Calendar Form</font><br />
  <br />  
	<form action="/calendar" method="POST">
		room: <input type="text" name="meetingRoomId" /><br /> 
		regUser: <input type="text" name="regUser" /><br /> 
		reg time:<br /> 
		regYmd: <input type="text" name="regYmd" /><br /> 
		startTime: <input type="text" name="startTime" /><br /> 
		endTime: <input	type="text" size="1" name="endTime" /> 
		<br /> 		
	  <input type="submit" value="add Calendar" />				
	</form>				
	<form name="return" action="menuclient.jsp" method='post'>
    <input type='submit' value='Return Menu Client'/>
  </form>
  <form name="return" action="home.jsp" method='post'>
    <input type='submit' value='Return Home'/>
  </form>
  
</body>
</html>