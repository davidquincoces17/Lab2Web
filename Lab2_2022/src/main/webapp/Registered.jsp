<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style>
h2 {
	font-weight:bold;
	color:darkgrey;
	margin-left: 20px;
	margin-top: 20px;
}

</style>

<body>
	<img src="imgs/FunnierSmall.png" alt= "logo" style="height:80px;">
	<h2> ${model.username} has been registered correctly! </h2>
</body>
</html>