<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link href="/ex/resources/css/style6.css" rel="stylesheet"/> 
<title>Insert title here</title>
</head>
<body>
	<div class=logo_container>
      <a href="simpleshop" id="logo">
        <img src="https://bakey-api.codeit.kr/files/629/images/logo.png" height="20" width="149">
      </a>
      <ul id="menu">
        <li><a href="cart?id=<%=session.getAttribute("id")%>">Cart</a></li>
        <%if(session.getAttribute("ValidMem")!=null){ %>
        	<li><a href="mypage?id=${id}">마이페이지</a></li>
        	<li><a href="logout">로그아웃</a></li>
        	<%} 
        else{
        	%>
        	<li><a href="login">로그인</a>
        	<%} %>
        <li><a href="join">회원가입</a>
      </ul>
    </div>
</body>
</html>