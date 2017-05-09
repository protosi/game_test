<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../header.jsp" %>
<title>Insert title here</title>
</head>
<body>
<%@include file="../nav.jsp" %>

	<div class="container">
		<div class="page-header">
			<h1>page header</h1>
		</div>
		<div class="adZone row form-group">
		<!-- 자동크기 0 반응형 -->
		<ins class="adsbygoogle"
		     style="display:block"
		     data-ad-client="ca-pub-8851477327612550"
		     data-ad-slot="3556251625"
		     data-ad-format="auto"></ins>
		</div>
		
		
		<table class="table table-bordered visible-xs hidden-sm hidden-md hidden-lg">
			<tbody>
				<tr>
					<td class="col-xs-2"><strong>제목</strong></td>
					<td class="col-xs-10"><p>${board.title}</p></td>
				</tr>
				<tr>
					<td class="col-xs-2"><strong>작성자</strong></td>
					<td class="col-xs-10"><p>${board.writer}</p></td>
				</tr>
				<tr>
					<td class="col-xs-2"><strong>날짜</strong></td>
					<td class="col-xs-10"><p>${board.reg_date}</p></td>
				</tr>
				<tr>
					<td class="col-xs-2"><strong>조회수</strong></td>
					<td class="col-xs-10"><p>${board.viewcount}</p></td>
				</tr>

			</tbody>
		</table>
		
		<table class="table table-bordered hidden-xs visible-sm visible-md visible-lg">
			<tbody>
				<tr class="text-center">
					<td class="col-xs-2"><strong>제목</strong></td>
					<td class="col-xs-5"><p>${board.title}</p></td>
					<td class="col-xs-2"><strong>작성자</strong></td>
					<td class="col-xs-3"><p>${board.writer}</p></td>
				</tr>
				<tr class="text-center">
					<td class="col-xs-2  text-center"><strong>날짜</strong></td>
					<td class="col-xs-4"><p>${board.reg_date}</p></td>
					<td class="col-xs-2 "><strong>조회수</strong></td>
					<td class="col-xs-4"><p>${board.viewcount}</p></td>
				</tr>

		</table>
		<div class="col-xs-12" style="height:300px;">${board.content}</div>
	</div>


<%@include file="../footer.jsp" %>
</body>
</html>