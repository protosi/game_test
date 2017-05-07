package com.midtics.component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RecaptchaComponent {
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@Value("${reCaptcha.secret}")
	String secret;
	
	@Value("${reCaptcha.url}")
	String url;

	
	public boolean validate(String chaptcha, String remoteip) throws ClientProtocolException, IOException, URISyntaxException, JSONException
	{
		String json = 
		Request.Post(new URI(url)).bodyForm(Form.form().add("secret", secret).add("remoteip", remoteip).add("response", chaptcha).build()).execute().returnContent().asString();
		logger.info(json);
		JSONObject jsonObject = new JSONObject(json);
		return jsonObject.getBoolean("success");
	}
	
}
