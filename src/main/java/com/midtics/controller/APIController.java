package com.midtics.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midtics.service.EcosStatLogService;


@Controller
@RequestMapping("v2")
public class APIController {
	
	@Value("${server.context-path}")
	String context_path;
	
	@Autowired
	EcosStatLogService serviceEcosStatLog;
	
	private static final Logger log = LoggerFactory.getLogger(APIController.class);

	@RequestMapping(value = "/getStatData", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	String monthly(HttpServletRequest request, HttpServletResponse response) 
	{
		String category = request.getParameter("category");
		
		if(category == null)
		{
			return "{}";
		}
		
		return serviceEcosStatLog.selectLastOne(category).getValue();
	}	
}