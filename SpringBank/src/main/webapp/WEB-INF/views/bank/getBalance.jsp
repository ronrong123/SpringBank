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
${balance }
<h2>금액조회</h2>
<table border="1">
<tr>
	<th>balance_amt</th>
	<td>${balance.balance_amt} </td>
</tr>
<tr>
	<th>available_amt</th>
	<td>${balance.available_amt} </td>
</tr>
<tr>
	<th>bank_name</th>
	<td>${balance.bank_name} </td>
</tr>
<tr>
	<th>product_name</th>
	<td>${balance.product_name} </td>
</tr>
</table>
</body>
</html>