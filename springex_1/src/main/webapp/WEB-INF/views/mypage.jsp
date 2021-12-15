<%@page import="com.javalec.ex.dto.MemberDto"%>
<%@page import="com.javalec.ex.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
 
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td colspan="2">나의정보</td>
		</tr>
		<tr>
			<td>ID</td>
			<td>${contents.id}</td>
		</tr>
		<tr>
			<td>PW</td>
			<td>${contents.pw}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${contents.name}</td>
		</tr>
		<tr>
			<td>잔액</td>
			<td>${contents.balance}		<a href="charge">충전하시겠습니까?</a></td>
			
		</tr>
		
		<input type="button" value="정보수정" onclick="javascript:window.location='modify'">
	
</body>
</html>