package com.midtics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midtics.controller.service.EcosService;

@Controller
public class TestController {
	
	@Autowired
	EcosService service;
	
	@RequestMapping(value="/test")
	public String testFunction(Model model)
	{
		model.addAttribute("name", "SpringBlog from Millky");
		return "test_hello";
	}
	@RequestMapping(value="/")
	@ResponseBody
	public Object index(Model model)
	{
		return service.selectAll();
	}
}
