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
		  <font size="5">���� �߰�</font><br />
		  <br />  
		  <div class="input-group date">
			  <form action="/calendar/add/submit" method="POST">
					ȸ�ǽ�: <input type="text" name="roomId" /><br /> 
					�����ڸ�: <input type="text" name="regUser" /><br /> 
					��������: <input type="text" name="regYmd" value="${regYmd}" /><br/>
					����ð�: <input type="text" size=6 name="startTime" /> ~ <input size=6 type="text" name="endTime" /> <br/>
					�ݺ�����: <input type="radio" name="repeatYn" value="N" checked="checked">�ƴϿ� <input type="radio" name="repeatYn" value="Y">��<br/>
					�ݺ�Ƚ��: <input type="number" name="repeatCount" value="0" min="0"/><br />
					<br /> 		
				  <input type="submit" value="���� ���" />				
			  </form>
			  <br/>
			  <form name="return" action="/calendar" method='get'>
			    <input type='submit' value='��� ���'/>
			  </form>
		  </div>
		</div>
	</body>
</html>