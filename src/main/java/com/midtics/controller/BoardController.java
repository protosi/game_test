package com.midtics.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Value("${server.context-path}")
	String context_path;
	
	@RequestMapping("write")
	ModelAndView write(HttpServletRequest request, HttpServletResponse response)
	{
		String category = request.getParameter("category");
		if(category == null)
		{
			category = "freeboard";
		}
		ModelAndView mav = new ModelAndView("board/write");
		mav.addObject("context_path", context_path);
		mav.addObject("category", category);
		return mav;
	}

}
