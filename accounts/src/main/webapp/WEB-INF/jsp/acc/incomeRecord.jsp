<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../bootstrap.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="javascript" type="text/javascript"
	src="${basePath }js/my97/WdatePicker.js"></script>
<title>消费记录</title>
</head>
<body>

	<div class="container">
		<nav class="navbar navbar-default" role="navigation">
		<form class="navbar-form navbar-left" role="search">
			<div class="form-group">日期</div>
			<div class="form-group">
				<input type="text" class="form-control" onClick="WdatePicker()">
			</div>
			<div class="form-group">
				<input type="text" class="form-control" onClick="WdatePicker()">
			</div>
			<div class="form-group">
				哪位大神
			</div>
			<div class="form-group">
				<select name="createBy">
					<option value="0">奎哥</option>
					<option value="1">雪娃子</option>
				</select>
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
		</nav>

		<div class="table-responsive">

			<table class="table table-responsive table-hover ">
				<tr>
					<th>日期</th>
					<th>大神</th>
					<th>米</th>
					<th>级别</th>
					<th>备注</th>
				</tr>
				<tbody>
					<c:forEach var="da" items="${paginate.datas }">
						<tr>
							<td><fmt:formatDate value="${da.createDate }"
									pattern="yyyy-MM-dd" /></td>
							<td>${da.createBy eq 0 ? '奎哥':'雪娃子' }</td>
							<td>${da.sumMoney }</td>
							<td>${da.sumMoney>=10000?'土豪':'' }${da.sumMoney>7000 and da.sumMoney<10000?'高富帅':'' }
								${da.sumMoney>4000 and da.sumMoney<7000?'一般般':'' }
								${da.sumMoney<4000 ?'弱逼':'' }</td>
							<td>${da.remark }</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="5">
							<ul class="pagination  pull-right">
								<li><a href="#">&laquo;</a></li>
								<li><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#">&raquo;</a></li>
							</ul>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>