package com.midtics.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.midtics.api.format.BasicFormat;
import com.midtics.component.RecaptchaComponent;
import com.midtics.mybatis.domain.Board;
import com.midtics.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@Value("${server.context-path}")
	String context_path;
	
	@Autowired
	BoardService serviceBoard;
	
	@Autowired
	RecaptchaComponent recaptcha;
	
	
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
	
	@RequestMapping("/api/insert")
	String insert(HttpServletRequest request)
	{
		BasicFormat<Object> api = new BasicFormat<Object>();
		List<Board> list = new ArrayList<Board>();
		Board board = new Board();
		String referer = request.getHeader("Referer");
		try
		{
			if(request.getParameter("g-recaptcha-response") == null)
			{
				return "redirect:"+ referer;
			}
			else if(!recaptcha.validate(request.getParameter("g-recaptcha-response"), request.getRemoteAddr()))
			{
				logger.info("recaptcha Validate failture");
				return "redirect:"+ referer;
			};
		}
		catch(Exception e)
		{
			logger.info(e.getMessage());
			return "redirect:"+ referer;
		}
		
		board.setWriter(request.getParameter("username"));
		board.setPasswd(request.getParameter("password"));
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		
		
		list.add(board);
		try
		{
			serviceBoard.insertBatch(list);
			api.setResult(100, "success");
		}
		catch(Exception e)
		{
			
			return "redirect:"+ referer;
		}
		return "redirect:"+"/board/write";
	}

}
