<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../base.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>LOGIN SUCCESS HTML</title>
</head>
<body>
	<h2 align="center" style="margin-top: 200px;">欢迎你${subject.principal}，登录成功!!</h2>
	<br>
	<a href="${basePath }test/pag1.shtml">GO MY PAGE TEST 1</a><br>
	<a href="${basePath }test/pag22.shtml">GO MY PAGE TEST 2</a><br>
	<a href="${basePath }test/pag33.shtml">GO MY PAGE TEST 3</a><br>
	<a href="${basePath }test/pag44.shtml">GO MY PAGE TEST 4</a><br>
	<a href="${basePath }acc/index.shtml">GO MY SYSTEM</a>
</body>
</html>