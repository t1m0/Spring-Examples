<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<meta charset="utf-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Secure Zone</title>
		<!-- Bootstrap css -->
		<link href="/SpringSecurity/css/bootstrap.min.css" rel="stylesheet">
		<link href="/SpringSecurity/css/login.css" rel="stylesheet">
	</head>
	<body>
		<div class="container">
			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>
			<form class="form-signin" role="form" action="<c:url value='j_spring_security_check' />" method="POST">
				<h2 class="form-signin-heading">Please sign in</h2>
				<input name="username" type="text" class="form-control" placeholder="Username" required autofocus>
				<input name="password" type="password" class="form-control" placeholder="Password" required>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
		</div>
	</body>
</html>