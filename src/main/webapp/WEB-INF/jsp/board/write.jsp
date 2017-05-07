<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../header.jsp" %>
<script src="${context_path}/resources/js/ckeditor/ckeditor.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<link rel="stylesheet" type="text/css" href="${context_path}/resources/css/board/write.css"></link>
<script>
	// Replace the <textarea id="editor1"> with a CKEditor
	// instance, using default configuration.
	$(document).ready(function(){CKEDITOR.replace( 'textarea' )});
</script>
<title>Insert title here</title>
</head>
<body>
<%@include file="../nav.jsp" %>

	<div class="container">
		<div class="page-header">
			<h1>${category}</h1>
		</div>
		<div class="adZone">
		<!-- 자동크기 0 반응형 -->
		<ins class="adsbygoogle"
		     style="display:block"
		     data-ad-client="ca-pub-8851477327612550"
		     data-ad-slot="3556251625"
		     data-ad-format="auto"></ins>
		</div>
		<form>
			<div class="form-group">
				<label for="username" class="col-2 col-form-label">Name</label>
				<div class="col-10">
				<input class="form-control" type="text" id="username" name="username" placeholder="Username">
				</div>
			</div>
			<div class="form-group">
				<label for="title" class="col-2 col-form-label">Title</label>
				<div class="col-10">
				<input class="form-control" type="text" id="username" name="title" placeholder="Title">
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-2 col-form-label">Password</label>
				<div class="col-10">
				<input class="form-control" type="password" id="username" name="password" placeholder="Password">
				</div>
			</div>
			<div class="form-group">
				<label for="textarea" class="col-2 col-form-label">Context</label>
				<div class="col-10">
				<textarea name="editor1" id="textarea" rows="10" cols="80" name="textarea"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label for="captcha" class="col-2 col-form-label">Validate</label>
				<div class="col-10">
					<div class="g-recaptcha" data-sitekey="6LfmUSAUAAAAAJ9EE4h11-PpCGD-tOb1fxOcDXM6"></div>
				</div>
			</div>
		</form>
		
		
	</div>


<%@include file="../footer.jsp" %>
</body>
</html>