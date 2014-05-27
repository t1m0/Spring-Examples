<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
		<title>Application Exception</title>
	    <!-- Bootstrap css -->
	    <link href="/SpringMVC/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
		<div class="container">
			<div class="jumbotron center">
				<h1>Application Exception</h1>
				<p>The application exception <font color="red">com.t1m0.spring.SpringMVC.CustomException</font> was thrown.</p>
				<h2>Cause</h2>
				<p>${exception.message}</p>
				<h2>Hint</h2>
				<p>${exception.hint}</p>
			</div>
		</div>
	</body>
</html>