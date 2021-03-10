<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<form action="insertProduct" method="post" name="frm">
		product_id <input name="product_id" type="number"><br>
		product_name <input name="product_name" type="text"><br>
		product_price <input type="number" name="product_price"><br>
		product_info <input type="text" name="product_info"> <br>
		product_date <input type="date" name="product_date"><br>
		company <input type="text" name="company"><br>
		manager_id <input type="text" name="manager_id"><br>
		<button type="submit">등록하기</button>		
	</form>
</div>
</body>
</html>