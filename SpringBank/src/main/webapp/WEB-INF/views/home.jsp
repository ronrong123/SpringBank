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
<a href="pdf/empList">empList</a>
<a href="pdf/empList2?dept=50">empList2</a>
<a href="pdf/empList3">empList3</a>
<a href="insertFboard">파일업로드</a>
<div style="margin-top:20px;">
<iframe src="pdf/empList" width="500" height="700" style="display:inline-block;"></iframe>
<iframe src="pdf/empList3" width="500" height="700" style="display:inline-block;"></iframe>
</div>
</body>
</html>
