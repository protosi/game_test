package com.midtics.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RserveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.midtics.mybatis.domain.EcosRawLogAll;
import com.midtics.service.EcosRawLogService;
import com.midtics.service.EcosStatLogService;
import com.midtics.util.RConnector;


@Controller
@RequestMapping("v2")
public class HomeV2Controller {
	
	@Value("${server.context-path}")
	String context_path;

	@Autowired
	EcosRawLogService serviceEcosAllData;
	
	@Autowired
	EcosStatLogService serviceEcosStatLog;
	
	@Autowired
	RConnector rconnector;
	
	private static final Logger log = LoggerFactory.getLogger(HomeV2Controller.class);

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
	
	@RequestMapping("/graph")
	ModelAndView graph(HttpServletRequest request, HttpServletResponse response)
	{
		String category = request.getParameter("category");
		if(category == null)
		{
			category = "";
		}
		
		
		ModelAndView mav = new ModelAndView("graph");
		mav.addObject("context_path", context_path);
		mav.addObject("category", category);
		return mav;
	}
	
	
}