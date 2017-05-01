package com.midtics.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midtics.controller.service.EcosService;
import com.midtics.mybatis.domain.EcosRawLogAll;

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
		List<EcosRawLogAll> list = new ArrayList<EcosRawLogAll>();
		
		
		for(int i = 0 ; i < 10 ; i++)
		{
		EcosRawLogAll test1 = new EcosRawLogAll();
		test1.setCYCLE("mm");
		
		test1.setDATA_VALUE(String.valueOf(Math.random()));
		test1.setITEM_CODE(String.valueOf(Math.random()));
		test1.setITEM_NAME1(String.valueOf(Math.random()));
		test1.setITEM_NAME2(String.valueOf(Math.random()));
		test1.setITEM_NAME3(String.valueOf(Math.random()));
		test1.setITEM_NAME(String.valueOf(Math.random()));
		test1.setUNIT_NAME(String.valueOf(Math.random()));
		test1.setSTAT_CODE(String.valueOf(Math.random()));
		test1.setSTAT_NAME(String.valueOf(Math.random()));
		test1.setTIME(String.valueOf(Math.random()));
		list.add(test1);
		}
		service.insertBatch(list);
		
		
		
		return service.selectAll();
	}
}
