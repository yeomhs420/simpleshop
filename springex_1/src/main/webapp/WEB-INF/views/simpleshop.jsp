<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>

<html>
  <head>
    <title>SimpleShop</title>
    <meta charset="utf-8" />
    <link href="/ex/resources/css/style5.css" rel="stylesheet"/> 
    
  </head>
  

  <body>
    <jsp:include page="header.jsp" />
 
    <div class="background_photo"></div>

    <div class="Products">
      <h1>Our New Products</h1>
      <div class="text">
        <a href="#"><img src="https://bakey-api.codeit.kr/files/629/images/sunglasses.jpg"
      width="225" height="225"></a>
        <h2>Sunglasses</h2>
        <p>49,000 
        	<input type='button' value='구매' onClick="location.href='buyOk?amount=49000&id=<%=session.getAttribute("id")%>'"/>
        	<input type='button' value='찜' onClick="location.href='insertitem?id=<%=session.getAttribute("id")%>&image_url=https://bakey-api.codeit.kr/files/629/images/sunglasses.jpg&name=Sunglasses&price=49000&amount=1'"/>
        </p>
        
      </div>

      <div class="text">
        <a href="#"><img src="https://bakey-api.codeit.kr/files/629/images/classic_loafer.jpg"
      width="225" height="225"></a>
        <h2>Tassel Loafer</h2>
        <p>89,000
        	<input type='button' value='구매' onClick="location.href='buyOk?amount=89000&id=<%=session.getAttribute("id")%>'"/>
        	<input type='button' value='찜' onClick="location.href='insertitem?id=<%=session.getAttribute("id")%>&image_url=https://bakey-api.codeit.kr/files/629/images/classic_loafer.jpg&name=Tassel Loafer&price=89000&amount=1'"/>
        </p>
        
      </div>
      <div class="text">
        <a href="#"><img src="https://bakey-api.codeit.kr/files/629/images/beige_bag.jpg"
      width="225" height="225"></a>
        <h2>beige bag</h2>
        <p>69,000
        	<input type='button' value='구매' onClick="location.href='buyOk?amount=69000&id=<%=session.getAttribute("id")%>'"/>
        	<input type='button' value='찜' onClick="location.href='insertitem?id=<%=session.getAttribute("id")%>&image_url=https://bakey-api.codeit.kr/files/629/images/beige_bag.jpg&name=beige bag&price=69000&amount=1'"/>
        </p>
        
      </div>
      <div class="text">
        <a href="#"><img src="https://bakey-api.codeit.kr/files/629/images/sneakers.jpg"
      width="225" height="225"></a>
        <h2>Sneakers</h2>
        <p>79,000
        	<input type='button' value='구매' onClick="location.href='buyOk?amount=79000&id=<%=session.getAttribute("id")%>'"/>
        	<input type='button' value='찜' onClick="location.href='insertitem?id=<%=session.getAttribute("id")%>&image_url=https://bakey-api.codeit.kr/files/629/images/sneakers.jpg&name=Sneakers&price=79000&amount=1'"/>
        </p>
      </div>
      <div class="text">
        <a href="#"><img src="https://bakey-api.codeit.kr/files/629/images/slippers.jpg"
      width="225" height="225"></a>
        <h2>Slippers</h2>
        <p>29,000
        	<input type='button' value='구매' onClick="location.href='buyOk?amount=29000&id=<%=session.getAttribute("id")%>'"/>
        	<input type='button' value='찜' onClick="location.href='insertitem?id=<%=session.getAttribute("id")%>&image_url=https://bakey-api.codeit.kr/files/629/images/slippers.jpg&name=Slippers&price=29000&amount=1'"/>
        </p>
      </div>
      <div class="text">
        <a href="#"><img src="https://bakey-api.codeit.kr/files/629/images/wrist_watch.jpg"
      width="225" height="225"></a>
        <h2>Wrist Watch</h2>
        <p>99,000
        	<input type='button' value='구매' onClick="location.href='buyOk?amount=99000&id=<%=session.getAttribute("id")%>'"/>
        	<input type='button' value='찜' onClick="location.href='insertitem?id=<%=session.getAttribute("id")%>&image_url=https://bakey-api.codeit.kr/files/629/images/wrist_watch.jpg&name=Wrist Watch&price=99000&amount=1'"/>
        </p>
      </div>
      <div class="text">
        <a href="#"><img src="https://bakey-api.codeit.kr/files/629/images/fedora_hat.jpg"
      width="225" height="225"></a>
        <h2>Fedora Hat</h2>
        <p>39,000
        	<input type='button' value='구매' onClick="location.href='buyOk?amount=39000&id=<%=session.getAttribute("id")%>'"/>
        	<input type='button' value='찜' onClick="location.href='insertitem?id=<%=session.getAttribute("id")%>&image_url=https://bakey-api.codeit.kr/files/629/images/fedora_hat.jpg&name=Fedora Hat&price=39000&amount=1'"/>
        </p>
      </div>
      <div class="text">
        <a href="#"><img src="https://bakey-api.codeit.kr/files/629/images/classic_loafer.jpg"
      width="225" height="225"></a>
        <h2>Classic Loater</h2>
        <p>99,000
        	<input type='button' value='구매' onClick="location.href='buyOk?amount=99000&id=<%=session.getAttribute("id")%>'"/>
        	<input type='button' value='찜' onClick="location.href='insertitem?id=<%=session.getAttribute("id")%>&image_url=https://bakey-api.codeit.kr/files/629/images/classic_loafer.jpg&name=Classic Loater&price=99000&amount=1'"/>
        </p>
      </div>
      <div class="text">
        <a href="#"><img src="https://bakey-api.codeit.kr/files/629/images/pink_bag.jpg"
      width="225" height="225"></a>
        <h2>Pink Bag</h2>
        <p>79,000
        	<input type='button' value='구매' onClick="location.href='buyOk?amount=79000&id=<%=session.getAttribute("id")%>'"/>
        	<input type='button' value='찜' onClick="location.href='insertitem?id=<%=session.getAttribute("id")%>&image_url=https://bakey-api.codeit.kr/files/629/images/pink_bag.jpg&name=Pink Bag&price=79000&amount=1'"/>
        </p>
      </div>
      <div class="clearfix"></div>
    </div>
    <div class=footer>
      <a href="http://facebook.com"> 
        <img src=https://bakey-api.codeit.kr/files/629/images/facebook.png height="20" id=img>
      </a>
      <a href="http://instagram.com">
        <img src=https://bakey-api.codeit.kr/files/629/images/instagram.png height="20" id=img>
      </a>
      <a href="http://twitter.com">
        <img src=https://bakey-api.codeit.kr/files/629/images/twitter.png height="20" id=img>
      </a>
    </div>
  </body>
</html>
