<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../base.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>PAGE TEST 3</title>
<script type="text/javascript">
	$(document).ready(function() {
		var mytb1 = $("#mytb1");
		var ab = new Paginate();
		ab.setTable(mytb1);
		ab.setDefaultPaging(false);
		initPaginateObj(ab); //全局化该对象
		ab.getPaginateObj();

	});

	function mytb1(Data) {
		var mytb1 = $("#mytb1");
		mytb1.find("tr:not(:first)").remove();
		var dataPair = [ "name", "age", "address", "department" ];
		$.each(Data.datas, function(ind, obj) {
			var trh = "<tr>";
			$.each(dataPair, function(dind, dobj) {
				trh += "<td>" + eval("obj." + dobj) + "</td>";
			});
			trh += "</tr>";
			mytb1.append(trh);
		});
		var footerhtm = "<tr style=\"font-size: small;\">";
		footerhtm += "<td colspan='" + dataPair.length + "'>";
		footerhtm += Data.footer + "</td></tr>";
		mytb1.append(footerhtm);
	}
</script>
</head>
<body>
	<br>
	<form action="${basePath }test/pag2.shtml" method="post" id="aaaa">
		<input type="hidden" name="pageSize" value="10"> <input
			type="hidden" name="pageNo" value="1"> <input type="hidden"
			id="pageAllNo" value="22">
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