package com.midtics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping(value="test")
	public String testFunction(Model model)
	{
		model.addAttribute("name", "SpringBlog from Millky");
		return "test_hello";
	}

}
