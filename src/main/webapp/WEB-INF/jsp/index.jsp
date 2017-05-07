<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="header.jsp" %>
<title>RINFO: View Detail, Be Smart!</title>
</head>
<body>
	<%@include file="nav.jsp" %>
	<div class="jumbotron main_image">
		<div class="jumbotron_content">
			<h1><strong>RINFO</strong></h1> 
			<h2><strong>View Detail, Be Smart!</strong></h2> 
		</div>
	</div>
		
	<div class="container">
		<h2><strong>RINFO KOSPI</strong>(Beta)</h2>
		<p>
			환율, 금리, 물가, 경상수지, 실업률 등등<br/>
			주가지수에 영향을 미치는 요인은 정말 다양합니다. RINFO는 종합주가지수(KOSPI)에 영향을 미칠 수 있는 각종 경제 지표를 수집하여 
			VAR 모형(<a href="https://en.wikipedia.org/wiki/Vector_autoregression">Vector Autoregression Model</a>)분석을 통해 예측 자료를 도출하고 있습니다. 
			RINFO KOSPI는 이렇게 만들어진 자료를 일간, 월간 보고서 형태로 제공하고 있습니다.      
		</p>
		<div class="row">
			<div class="col-sm-3">
				<h3>수집</h3> 
				<span><a href="http://ecos.bok.or.kr">한국은행</a>에서 제공하는 각종 경제지표와  통계 자료를 수집합니다.</p>
				
			</div>
			<div class="col-sm-3">
				<h3>분석</h3> 
				<span>수집된 통계 자료를 <a href="https://www.r-project.org/">R programming</a>을 이용하여 다양한 통계기법으로 분석합니다.</p>
			</div>
			<div class="col-sm-3">
				<h3>보고</h3> 
				<span>분석한 결과를 저장하고 WEB으로 제공함으로써, 언제 어디서나 통계 보고서를 편리하게 이용 가능합니다.</p>
			</div>
			<div class="col-sm-3">
				<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
				<!-- 와이드 스카이스크래퍼 -->
				<ins class="adsbygoogle"
				     style="display:inline-block;width:160px;height:600px"
				     data-ad-client="ca-pub-8851477327612550"
				     data-ad-slot="3277050028"></ins>
				<script>
				(adsbygoogle = window.adsbygoogle || []).push({});
				</script>
			</div>
		</div>
	</div>
	
	<%@include file="footer.jsp"%>
</body>
</html>