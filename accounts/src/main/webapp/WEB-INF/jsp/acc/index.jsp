<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../base.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>嘿嘿嘿</title>
<style type="text/css">
.content {
	height: 150px;
	width: 200px;
}

.removeXhx {
	text-decoration: none;
	color: black;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {
		$("table").find("a").addClass("removeXhx");
 	});
</script>
</head>
<body>

	<table align="center" style="margin-top: 80px; width: 600px;">
		<tr>
			<th colspan="3">
				<h2>欢迎访问管家婆V1.0</h2>
			</th>
		</tr>
		<tbody>
			<tr style="height: 150px;">
				<td class="content"><img src="${basePath }img/1.jpg"
					style="height: 50px; width: 50px;"><a
					href="${basePath }acc/addMoney.shtml">哈哈哈,有钱了</a></td>
				<td class="content"><img src="${basePath }img/2.jpg"
					style="height: 50px; width: 50px;"><a href="${basePath }acc/consumeRecord.shtml">T T又花钱了</a></td>
				<td class="content"><img src="${basePath }img/3.jpg"
					style="height: 50px; width: 50px;">看看报表</td>
			</tr>
		</tbody>
	</table>
</body>
</html>