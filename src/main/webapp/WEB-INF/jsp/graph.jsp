<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="header.jsp" %>
		<script src="${context_path}/resources/js/d3.v3.js"></script>
		<script src="${context_path}/resources/js/graph/graph.js"></script>
		<link rel="stylesheet" type="text/css" href="${context_path}/resources/css/graph/graph.css"></link>
	<script>
		var context_path = "${context_path}";
		$(document).ready(function(){
			//makeGraph();
			$.ajax("${context_path}/v2/getStatData?category=${category}").done(function(data){
				if(typeof data == 'string' )
				{
					data = JSON.parse(data);
				}
				var names = Object.getOwnPropertyNames(data);
				var width = $('div.container-graph').width();
				var height = width < 600 ? 300 : width * 0.5;
				for(var i = 0 ; i < names.length ; i++)
				{
					drawGraph(names[i], data[names[i]], width, height);
				}
			}
			);
		});
	</script>
	</head>
	
<body>
	<%@include file="nav.jsp" %>
	
	<div class="container">
		<div class="page-header">
			<h1>KOSPI Report</h1>
			<p><strong>Stat Code: </strong>${category}</p>
		</div>
		<div class="adZone">
		<!-- 자동크기 0 반응형 -->
		<ins class="adsbygoogle"
		     style="display:block"
		     data-ad-client="ca-pub-8851477327612550"
		     data-ad-slot="3556251625"
		     data-ad-format="auto"></ins>
		</div>
		<div class="container-graph">
	
		</div>
	</div>
	
	<%@include file="footer.jsp" %>
</body>
</html>

