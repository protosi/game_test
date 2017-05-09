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
		<div class="row">
			<div class="col-sm-4">
				<div class="form-group">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="제목 또는 본문 검색"/>
						<span class="input-group-btn">
							<button class="btn btn-default" type="button">검색</button>
						</span>
					</div>
				</div>
			</div>
			
			<div class="col-sm-8 text-right">
				<div class="form-group">
					<a href="write?category=${category}" class="btn btn-info" >글쓰기</a>
				</div>
			</div>
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
		<div class="form-group text-center ng-hide"  ng-show="data.recv.list.length > 0">
			<ul class="pagination" >
				<li><a href="./list?page=1&category=${category}">Prev</a></li> 
				<li ng-show="data.recv.page >= 3"><a href="./list?page={{data.recv.page - 2}}&category=${category}">{{data.recv.page - 2}}</a></li>
				<li ng-show="data.recv.page >= 2"><a href="./list?page={{data.recv.page - 1}}&category=${category}">{{data.recv.page - 1}}</a></li>
				<li><a href="./list?page={{data.recv.page}}&category=${category}">{{data.recv.page}}</a></li>
				<li ng-show="total > list_size * page" ><a href="./list?page={{data.recv.page + 1}}&category=${category}">{{data.recv.page + 1}}</a></li>
				<li ng-show="total > list_size * (page + 1)" ><a href="./list?page={{data.recv.page +2}}&category=${category}">{{data.recv.page + 2}}</a></li>
				<li><a href="./list?page={{Math.floor((data.recv.total - 1) / list_size)+1}}&category=${category}">Next</a></li>
			</ul>
		</div>
	</div>


<%@include file="../footer.jsp" %>
</body>
</html>