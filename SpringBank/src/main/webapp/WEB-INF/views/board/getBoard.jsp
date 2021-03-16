<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
seq : ${board.seq }<br>
title : ${board.title }<br>
writer : ${board.writer }<br>
content : ${board.content }<br>
첨부파일 단건 : <a href="fileDown?seq=${board.seq }">${board.filename }</a><br>
첨부파일 다건:
<a href="#">일괄다운받기(zip)</a><br>
<c:forTokens items="${board.filename }" delims="," var="file">
 	<a href="fileNameDown?filename=${file }">${file }</a><br>
 </c:forTokens>
</body>
</html>