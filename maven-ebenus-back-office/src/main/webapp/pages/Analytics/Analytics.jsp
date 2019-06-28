<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../../components/Header.jsp"%>
<%@include file="../../components/NavBar.jsp"%>

<!DOCTYPE HTML>

<html>

<head>
	
	<% Header();%>
	
	<script src= "./assets/js/analytics.js"></script>

</head>

<body>

	<% NavBar();%>
	
	<div class="content">
	
		<div class="chartContainer">
		
			<div id="div1">
				<div id="typeDonutChart"></div>
			</div>
			
			<div id="div2">
				<div id="timeSeriesChart"></div>
			</div>
			
			<div id="div3">
				<div id="roleDonutChart"></div>
			</div>
			
		</div>
		
	</div>

</body>

</html>