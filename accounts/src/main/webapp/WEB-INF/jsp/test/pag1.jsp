<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../base.jsp"%>
<html>
<head>
<script type="text/javascript" src="${basePath }js/jquery.1.11.min.js"></script>
<script type="text/javascript" src="${basePath }js/paginate.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>PAGE TEST 1</title>
</head>
<body>
	<br>
	<form action="${basePath }test/pag1.shtml" method="post">
		<table id="mytb1" align="center">
			<thead>
				<tr>
					<th>姓名</th>
					<th>年龄</th>
					<th>地址</th>
					<th>部门</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dat" items="${paginate.datas }">
					<tr>
						<td>${dat.name }</td>
						<td>${dat.age }</td>
						<td>${dat.address }</td>
						<td>${dat.department }</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot style="font-size: small;">
				<tr>
					<td colspan="4" align="right">
						${paginate.footer }
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
</body>
</html>