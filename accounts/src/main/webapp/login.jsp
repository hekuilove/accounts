<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	// 获得本项目的地址(例如: http://localhost:8080/MyApp/)赋值给basePath变量    
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	// 将 "项目路径basePath" 放入pageContext中，待以后用EL表达式读出。    
	pageContext.setAttribute("basePath", basePath);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>APACHE SHIRO LOGIN DEMO</title>
</head>
<body>
	<form action="${basePath }usr/login.shtml" method="post">
		<table align="center" style="margin-top: 150px;">
			<tr>
				<th align="center" colspan="2"><h2>APACHE SHIRO LOGIN</h2></th>
			</tr>
			<tr>
				<td align="right" width="40%">LOGIN_ID</td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td align="right" width="40%">LOGIN_PASSWORD</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<th align="center" colspan="2"><h2>
						<input type="submit" value="登录">
					</h2></th>
			</tr>
		</table>
	</form>
</body>
</html>