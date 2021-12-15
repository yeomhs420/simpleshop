<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="chargemoney?id=<%=session.getAttribute("id")%>" method="post">
		얼마를 충전하시겠습니까?<input type="number" name="money">
		<input type="submit" value="충전">
	</form>
	
</html>