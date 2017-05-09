/**
 * 
 */
$(document).ready(function(){
	CKEDITOR.replace( 'textarea' );
	CKEDITOR.config.height=300;
});


function insert()
{
	var rawParam = $('form').serializeArray();
	var param = {};
	for(var i = 0 ; i < rawParam.length ; i++)
	{
		var name = rawParam[i]["name"];
		var value = rawParam[i]["value"].trim();
		param[name] = value;
		

	}
	
	
	if(param["title"] == "")
	{
		alert("제목을 입력해주세요.");
		$('input[name="title"]').focus();
		return;
	}
	if(param["username"] == "")
	{
		alert("이름을 입력해주세요.");
		$('input[name="username"]').focus();
		return;
	}
	if(param["password"] == "")
	{
		alert("비밀번호를 입력해주세요.");
		$('input[name="password"]').focus();
		return;
	}
	if(CKEDITOR.instances.textarea.getData().length == 0)
	{
		alert("내용을 입력해주세요.");
		CKEDITOR.instances.textarea.focus();
		return;
	}
	if(param["g-recapcha-response"] == "")
	{
		alert("리캡차를 선택해주세요.");
		$('input[name="g-recapcha-response"]').focus();
		return;
	}
	
	$('form').submit();
}