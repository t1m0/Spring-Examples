<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8"/>
		<title>Exception</title>
	    <!-- Bootstrap css -->
	    <link href="/SpringMVC/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
		<div class="container">
			<div class="jumbotron center">
				<h1>Exception</h1>
				<p>An unexpected exception was thrown.</p>
				<h2>Cause</h2>
				<p>${exception.message}</p>
			</div>
		</div>
	</body>
</html>