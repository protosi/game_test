<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-inverse nomargin navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button class="navbar-toggle" data-target="#bs-navbar" data-toggle="collapse" type="button">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${context_path}/">RINFO</a>
		</div>
		<div class="collapse navbar-collapse"  id="bs-navbar">
			<ul class="nav navbar-nav " id="bs-navbar">
				<li><a href="${context_path}/">Home</a></li>
				<li><a href="#" class="dropdown-toggle" data-toggle="dropdown">KOSPI
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="${context_path}/v2/graph?category=monthly_stat_01">Monthly Report 01</a></li>
						<li><a href="${context_path}/v2/graph?category=monthly_stat_02">Monthly Report 02</a></li>
						<li><a href="${context_path}/v2/graph?category=daily_stat_01">Daily Report 01</a></li>
					</ul>
				</li>
				<li><a href="#" class="dropdown-toggle" data-toggle="dropdown">분석방법<span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="#">VAR모형</a></li>
				</ul>
				
				</li>
				<li><a href="#" class="dropdown-toggle" data-toggle="dropdown">Board<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">게시판1</a></li>
					</ul>
				</li>
				<li><a href="#">Contact us</a></li>
			</ul>
		</div>
	</div>
</nav>