package com.midtics.controller;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midtics.service.EcosRawLogService;
import com.midtics.util.RConnector;

@Controller
public class TestController {
	
	@Autowired
	RConnector rconnect;
	
	@Autowired
	EcosRawLogService service;
	
	@RequestMapping(value="/test")
	public String testFunction(Model model)
	{
		model.addAttribute("name", "SpringBlog from Millky");
		return "test_hello";
	}
	@RequestMapping("/")
	@ResponseBody
	public Object index(Model model) throws RserveException, REXPMismatchException{

		
			rconnect.initialize();
			double[] d = rconnect.getConnection().eval("rnorm(100)").asDoubles();
			
			rconnect.close();
		
		
		
		return d;
	}
}
