<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>자료실</h3>
	<form action="insertFboard" method="post" encType="multipart/form-data">
	<!-- input type="file"이 있으면 post방식으로 넘기고 encType="multipart/form-data"가 필수로 들어감 -->
		작성자<input type="text" name="writer"><br /> 
		제목<input type="text" name="title"><br /> 
		내용 <textarea name="content"></textarea><br /> 
		첨부파일<input type="file" name="uploadFile" multiple="multiple" /><br /> 
		<!-- multiple로 받으면 파일을 여러개 받을수있음(이떄 배열로 받아야함) -->
		<input type="submit" value="저장">
	</form>
</body>
</html>