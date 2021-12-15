<%@page import="com.javalec.ex.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
  
<%
	request.setCharacterEncoding("EUC-KR");
	String id = request.getParameter("id");
	int amount = Integer.parseInt(request.getParameter("amount"));
	
	if(id.equals("null")){
%>
	<script language="javascript">
		alert("로그인 후 이용가능합니다.");
		history.go(-1);
	</script>

<% }
	else{
	
	MemberDao dao = MemberDao.getInstance();
	int checkNum = dao.buyitem(id, amount);
	if(checkNum == 0) {
%>
		<script language="javascript">
			alert("금액이 부족합니다.");
			history.go(-1);
		</script>
<%} 
	else if(checkNum==1){
%>
	<script language="javascript">
		alert("구매가 완료되었습니다.");
		history.go(-1);
	</script>
<%} 
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