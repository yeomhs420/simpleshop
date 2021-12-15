<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<c:set var="total_price" value="0"/>

	<table width="500" cellpadding="0" cellspacing="0" border="1" style="margin: auto; text-align: center;">
	<tr bgcolor="skyblue" align ="center">
		<p><td colspan = "4" style="font-size:30px">장바구니 목록</td></p>
    </tr>
	<tr>
			<th>상품</th>
			<th>상품명</th>
			<th>가격</th>
			<th>주문 수량</th>
	</tr>
     
	<c:forEach items="${DtoList}" var="dto">
		<tr>
			<td><img src=${dto.image_url} width="180"></td>
			<td>${dto.name}</td>
			<td>${dto.price}</td>
			<td>${dto.amount}</td>
		</tr>
		<c:set var= "total_price" value="${total_price + dto.price*dto.amount}"/>
		
	</c:forEach>
	
	총 금액:<c:out value="${total_price}"/>
	<input type='button' value='구매' onClick="location.href='buyOk?amount=${total_price}&id=<%=session.getAttribute("id")%>'"/>
	

</div>
          
	
</body>
</html>