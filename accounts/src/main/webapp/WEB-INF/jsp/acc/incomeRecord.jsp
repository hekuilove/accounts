<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../bootstrap.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="javascript" type="text/javascript"
	src="${basePath }js/my97/WdatePicker.js"></script>
<script type="text/javascript">
	
</script>
<title>收入记录</title>
</head>
<body>

	<div class="container">
		<nav class="navbar navbar-default" role="navigation">
		<form class="navbar-form navbar-left" role="search" id="form1" method="post">
			<div class="form-group">日期</div>
			<div class="form-group">
				<input type="text" name="time1" value="${param.time1}"
					class="form-control" onClick="WdatePicker()">
			</div>
			<div class="form-group">
				<input type="text" name="time2" value="${param.time2 }"
					class="form-control" onClick="WdatePicker()">
			</div>
			<div class="form-group">哪位大神${aaa==null }</div>
			<div class="form-group">
				<select name="createBy" class="form-control">
					<option value=""></option>
					<option value="0" ${paginate.bean.createBy eq 0 ? 'selected':'' }>奎哥</option>
					<option value="1" ${paginate.bean.createBy eq 1 ? 'selected':'' }>雪娃子</option>
				</select>
			</div>
			<button type="submit" class="btn btn-info"
				data-loading-text="正在加载...">Search</button>
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
							<td>${da.sumMoney>=10000?'土豪':'' }${da.sumMoney>7000 and
								da.sumMoney<10000?'高富帅':'' } ${da.sumMoney>4000 and
								da.sumMoney<7000?'一般般':'' } ${da.sumMoney<4000 ?'弱逼':'' }</td>
							<td>${da.remark }</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="5">
							<ul class="pagination  pull-right">
								<c:if test="${1 != paginate.pageNo }">
									<li><a
										href="javascript:doPaginate(${paginate.pageNo -1 },${paginate.pageSize },'form1')">&laquo;</a></li>
								</c:if>
								<c:forEach var="ps" items="${paginate.pageNos }">
									<li><a
										href="javascript:doPaginate(${ps },${paginate.pageSize },'form1')">${ps
											}</a></li>
								</c:forEach>
								<c:if test="${paginate.pageAllNo  != paginate.pageNo }">
									<li><a
										href="javascript:doPaginate(${paginate.pageNo +1 },${paginate.pageSize },'form1')">&raquo;</a></li>
								</c:if>
							</ul>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<p>
			
				<a class="btn btn-success btn-lg pull-right" href="${basePath }acc/index.shtml">Back to Home Page</a>
				<button type="button" class="btn btn-primary btn-lg pull-right"
				data-toggle="modal" data-target="#myModal">Add Income</button>
		</p>
	</div>

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Please Add Income
						Info</h4>
				</div>
				<form class="form-horizontal" role="form"
					action="${basePath }acc/addIncome.shtml" method="post">
					<div class="modal-body">

						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">Whom</label>
							<div class="col-sm-10">
								<select class="form-control" name="createBy">
									<option value="0">奎哥</option>
									<option value="1">雪雪</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">Money</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="sumMoney"
									placeholder="Input Money">
							</div>
						</div>

						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">Remark</label>
							<div class="col-sm-10">
								<textarea class="form-control" rows="3" name="remark"></textarea>
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
</body>
</html>

<script>
	
</script>