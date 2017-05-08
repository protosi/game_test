<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../header.jsp" %>
<script src="${context_path}/resources/js/angular.min.js"></script>
<script src="${context_path}/resources/js/board/list.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<link rel="stylesheet" type="text/css" href="${context_path}/resources/css/board/list.css"></link>
<title>Insert title here</title>
</head>
<body>
<%@include file="../nav.jsp" %>

	<div class="container" ng-app="myApp" ng-controller="listController">
		<div class="page-header">
			<h1>${category}</h1>
		</div>
		<div class="adZone row form-group">
		<!-- 자동크기 0 반응형 -->
		<ins class="adsbygoogle"
		     style="display:block"
		     data-ad-client="ca-pub-8851477327612550"
		     data-ad-slot="3556251625"
		     data-ad-format="auto"></ins>
		</div>
		<div class="wd-sm-12 text-right">
			<a href="write?category=${category}" class="btn btn-info" >글쓰기z</a>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th class="wd-sm-1">No</th>
					<th class="wd-sm-8">Title</th>
					<th class="wd-sm-1">Name</th>
					<th class="wd-sm-2 hidden-xs">Date</th>
				</tr>
		    </thead>
		    <tbody>
		    	<tr>
		    		<td colspan="4" ng-show="data.recv.list.length <= 0" class="nodata"> 검색 결과가 없습니다. </td>
		    	</tr>
		    	<tr ng-repeat="row in data.recv.list" class="ng-hide" ng-show="data.recv.list.length > 0">
						<td>{{row.id}}</td>
						<td><a href="read?id={{row.id}}"> {{row.title}}</a></td>
						<td>{{row.writer}}</td>
						<td class="hidden-xs">{{row.reg_date}}</td>
				</tr>
			
		    </tbody>
		</table>
		
	</div>


<%@include file="../footer.jsp" %>
</body>
</html>