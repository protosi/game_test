<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="${context_path}/resources/js/jquery-3.2.1.js"></script>
<script src="${context_path}/resources/js/bootstrap.js"></script>
<script src="${context_path}/resources/js/jquery.scrollify.js"></script>
<script src="${context_path}/resources/js/Nav/Nav.js"></script>
<link rel="stylesheet" type="text/css" href="${context_path}/resources/css/bootstrap.css"></link>
<link rel="stylesheet" type="text/css" href="${context_path}/resources/css/Nav/Nav.css"></link>
<link rel="stylesheet" type="text/css" href="${context_path}/resources/css/main/main.css"></link>
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="Nav.jsp" flush="true">
		<jsp:param value="test" name="test"/>
	</jsp:include>

	<div class="jumbotron main_image scroll-panel text-center">
			<div class="jumbotron_content">
				<h2><strong>View detail, Be Smart</strong></h2> 
				<p><strong>똑똑한 투자자가 되기 위해 봐야하는 통계 보고서</strong></p>
			</div>
		</div>
		
	<div class="container scroll-full-panel">
		<h1>My First Bootstrap Page</h1>
		<p>This is some text.</p> 
		<div class="row">
			<div class="col-sm-6">
				<h3>Column 3</h3> 
				<p>Lorem ipsum dolor..</p>
				<p>Ut enim ad..</p>
			</div>
			<div class="col-sm-6">
				<h3>Column 3</h3> 
				<p>Lorem ipsum dolor..</p>
				<p>Ut enim ad..</p>
			</div>
		</div>
	</div>
</body>
</html>