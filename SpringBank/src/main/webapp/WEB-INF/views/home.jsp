<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8"  %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<P>  The time on the server is ${serverTime}. </P>
<a href="getAccountList">전체계좌조회</a>
<a href="getOrgAuthorize">기관인증</a>
<a href="getBiz">크롤링</a>
</body>
</html>
