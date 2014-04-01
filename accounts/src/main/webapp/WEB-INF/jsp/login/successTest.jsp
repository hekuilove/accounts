<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>LOGIN SUCCESS HTML</title>
</head>
<body>
	<h2 align="center" style="margin-top: 200px;">
		欢迎你
		<shiro:principal />
		，登录成功!!
	</h2>
	<br>
	<shiro:hasRole name="管理员">
		拥有管理员权限
	</shiro:hasRole>
	<shiro:hasRole name="打酱油">
		拥有打酱油权限
	</shiro:hasRole>
	<shiro:hasAnyRoles name="管理员,打酱油">
		拥有管理员、打酱油权限
	</shiro:hasAnyRoles>
	<br>
	<shiro:notAuthenticated>
		未验证权限!
	</shiro:notAuthenticated>
	<br>
	<shiro:guest>
		我是游客!
	</shiro:guest>
	<br>
	<shiro:user>
		我是用户
	</shiro:user>
</body>
</html>