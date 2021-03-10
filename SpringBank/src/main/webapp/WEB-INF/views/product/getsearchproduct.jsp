<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table{border-collapse: collapse;margin:40px;}
</style>
</head>
<body>
<div align="center">
	<table border="1">
	<c:forEach items="${list }" var="pro">
		<tr>
			<td>${pro.product_id }</td>
			<td>${pro.product_name }</td>
			<td>${pro.product_price }</td>
			<td>${pro.product_info }</td>
			<td>${pro.product_date }</td>
			<td>${pro.company }</td>
			<td>${pro.manager_id }</td>
		</tr>
	</c:forEach>	
	</table>
	<a href="insertProduct">제품등록하기</a>
</div>
</body>
</html>