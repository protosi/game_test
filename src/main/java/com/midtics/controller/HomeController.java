package com.midtics.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@Value("${server.context-path}")
	String context_path;
	
	@RequestMapping("/")
	
	public String index(Model model) {
		model.addAttribute("context_path", context_path);
		return "index";
	}
}
