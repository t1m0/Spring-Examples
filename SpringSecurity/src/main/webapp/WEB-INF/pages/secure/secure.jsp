<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8"/>
	    <title>Secure Zone</title>
	    <!-- Bootstrap css -->
	    <link href="/SpringSecurity/css/bootstrap.min.css" rel="stylesheet">
	    <script type="text/javascript">
			function formSubmit() {document.getElementById("logoutForm").submit();}
		</script>
	</head>
	<body role="document">
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<!-- csrt for log out-->
		<form action="${logoutUrl}" method="post" id="logoutForm">
	  		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		<div class="container theme-showcase" role="main">
			<div class="jumbotron">
	        	<h1>Welcome in the secure zone</h1>
	        	<p>You shouldn't be able to access this page without being logged in.</p>
	        	<button class="btn btn-lg btn-danger btn-block" type="button" onclick="javascript:formSubmit()">Logout</button>
	      	</div>
		</div>
	</body>
</html>
