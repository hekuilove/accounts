<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../base.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>PAGE TEST 2</title>
<script type="text/javascript">
	$(document).ready(function() {
		initTab();
		var	 a = $("#aaaa").serialize();
		alert(a);
	});

	function initTab() {
		/* var pg = new Paginate(); */
		$.ajax({
			url : "${basePath}test/pag2.shtml",
			type : "post",
			dataType : "json",
			success : function(P) {
				$.each(P.datas, function(ind, obj) {
				})
			},
			error : function() {
				alert("success");
			}
		})
	}
</script>
</head>
<body>
	<br>
	<form action="${basePath }test/pag1.shtml" method="post" id="aaaa">
	<input type="hidden" name="aa" value="11">
	<input type="hidden" name="bb" value="11">
	<input type="hidden" name="cc" value="11">
		<table id="mytb1" align="center">
			<thead>
				<tr>
					<th>姓名</th>
					<th>年龄</th>
					<th>地址</th>
					<th>部门</th>
				</tr>
			</thead>
		</table>
	</form>
</body>
</html>