<%@page import="com.javalec.ex.dao.ProductDao"%>

<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%	
	String id=request.getParameter("id");
    String name=request.getParameter("name");
    int amount=Integer.parseInt(request.getParameter("amount"));
    int price=Integer.parseInt(request.getParameter("price"));
    String image_url=request.getParameter("image_url");


	if(id.equals("null")){
%>
	<script language="javascript">
		alert("로그인 후 이용가능합니다.");
		history.go(-1);
	</script>
	
<%}
	else{
		ProductDao dao=ProductDao.getInstance();
		int CheckNum = dao.imageCheck(image_url);
		
		if(CheckNum == 0){
			dao.insertproduct(id,name,amount,price,image_url);
%>
	<script language="javascript">
		alert("장바구니에 추가 되었습니다.");
		history.go(-1);
	</script>
<%} 
		else{
		dao.plusamount(name);
%>
		<script language="javascript">
		alert("수량이 추가 되었습니다.");
		history.go(-1);
		</script>
<% 
		}
	}
%>
		
		


<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	
</body>
</html>