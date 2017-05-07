/**
 * 
 */
$(document).ready(function(){
	CKEDITOR.replace( 'textarea' );
	CKEDITOR.config.height=500;
});


function insert()
{
	var rawParam = $('form').serializeArray();
	var param = {};
	for(var i = 0 ; i < rawParam.lengh ; i++)
	{
		var name = rawParam[i]["name"];
		var value = rawParam[i]["value"].trim();
		param[name] = value;
		
		if(value == '')
		{
			$('input[name="'+name+'"]').focus();
		}
	}
	
	if(param["title"] == "")
	{
		alert("제목을 입력해주세요.");
		return;
	}
	if(param["username"] == "")
	{
		alert("이름을 입력해주세요.");
		return;
	}
	if(param["password"] == "")
	{
		alert("비밀번호를 입력해주세요.");
		return;
	}
	if(param["content"] == "")
	{
		alert("비밀번호를 입력해주세요.");
		return;
	}
	if(param["g-recapcha-response"] == "")
	{
		alert("리캡차를 선택해주세요.");
		return;
	}
	
	$('form').submit();
}