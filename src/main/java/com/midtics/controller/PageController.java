package com.midtics.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
	
	@Value("${server.context-path}")
	String context_path;
	
	@RequestMapping("/")
	
	public String index(Model model) {
		model.addAttribute("context_path", context_path);
		return "index";
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
