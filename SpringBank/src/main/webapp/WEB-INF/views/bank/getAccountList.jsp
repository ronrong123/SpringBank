<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
 table{border-collapse: collapse;}
 tr{cursor:pointer;}
 td, th{padding:5px;}
</style>
</head>
<body>
${list }
<h3>계좌조회</h3>
<table border="1"> 
<tr>
<th>이름</th>
<th>은행</th>
<th>계좌번호</th>
<th>가상계좌</th>
</tr>
<c:forEach items="${list.res_list }" var="bank">
<tr onclick="getBalance('${bank.fintech_use_num }')">
	<td>${bank.account_alias }</td>
	<td>${bank.bank_name }</td>
	<td>${bank.account_num_masked }</td>
	<td>${bank.fintech_use_num }</td>
</tr>
</c:forEach>
</table>
<div id="result">
</div>
<script>
	function getBalance(fin){
		//ajax호출
		/* $.ajax({
			url:"ajacGetBalance",
			data:{fintech_use_num:fin},
			dataType:"json",
			success:function(response){
				$("#result").empty();
				$("#result").append($("<h3>").html("결과"));
				var table = $("<table>").attr("border","1");
				$("#result").append(table);
				//result안에있는 값을 삭제하는것,
				for(bank in response){
					var tr = $("<tr>")
					$(table).append($(tr));
					$(tr).append($("<td>").html([bank]));
					$(tr).append($("<td>").html(response[bank]));
				}
			},
			error:function(result){
				 console.log('에러: ' + result.statusText);
			}
		}); */
		//ajacGetBalance
		//fineetechUseNum = "+fin"
		$.ajax({
			url:"getBalance",
			data:{fintech_use_num:fin},
			dataType:"html",
			success:function(response){
				$("#result").html(response);
			},
			error:function(result){
				 console.log('에러: ' + result.statusText);
			}
		});
	}
</script>
</body>
</html>