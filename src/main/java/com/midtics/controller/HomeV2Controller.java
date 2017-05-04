package com.midtics.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RserveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	EcosRawLogService serviceEcosAllData;
	
	@Autowired
	EcosStatLogService serviceEcosStatLog;
	
	@Autowired
	RConnector rconnector;
	
	private static final Logger log = LoggerFactory.getLogger(HomeV2Controller.class);

	@RequestMapping("/monthly")
	@ResponseBody
	String monthly(HttpServletRequest request, HttpServletResponse response) 
	{
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			rconnector.initialize();
		} catch (RserveException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date _date = new Date();
		
		String Start_date = "200001";
		
		String end_date = sdf.format(_date);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -3);
		end_date = sdf.format(cal.getTime());
		System.out.println(end_date);
		
		if(request.getParameter("start") != null)
		{
			Start_date = request.getParameter("start");
		}
		if(request.getParameter("end") != null)
		{
			end_date = request.getParameter("end");
		}

		String envName = "temp" +System.nanoTime();
		
		List<String> name = new ArrayList<String>();
		;
		//name.add(makeDataFramefromRawData("stock_kor", "I01Y006", "KOR", Start_date, end_date, "%Y%m%d", "1 month", envName));
		//name.add(makeDataFramefromRawData("exchange_kor", "I04Y002", "KOR", Start_date, end_date, "%Y%m%d", "1 month", envName));
		//name.add(makeDataFramefromRawData("foreign_money_kor", "I04Y001", "KOR", Start_date, end_date, "%Y%m%d", "1 month", envName));
		name.add(makeDataFramefromRawData("maker_kor", "I02Y001", "KOR", Start_date, end_date, "%Y%m%d", "1 month", envName));
		name.add(makeDataFramefromRawData("customer_kor", "I02Y002", "KOR", Start_date, end_date, "%Y%m%d", "1 month", envName));
		name.add(makeDataFramefromRawData("industry_kor", "I05Y001", "KOR", Start_date, end_date, "%Y%m%d", "1 month", envName));
		name.add(makeDataFramefromRawData("trade_kor", "022Y013", "000000", Start_date, end_date, "%Y%m%d", "1 month", envName));

		for(String n : name)
		{
			try {
				System.out.println(n+" : "+ rconnector.getConnection().eval("length("+envName+"$"+n+"$value"+")").asString());
			} catch (REXPMismatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}

		
		String[] nameArray = new String[name.size()];
		JSONObject json = null;
		try {
			json= rconnector.forecastVAR(2, "both", 12, 0.80, envName, 
					name.toArray(nameArray));
			writer.print(json.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		// 환경을 해제한다.
		rconnector.getConnection().rm(envName);		
		rconnector.close();
		
		serviceEcosStatLog.insert("monthly",json.toString());
		
		return json.toString();
	}
	
	
	protected String  makeDataFramefromRawData(String name, String stat_code, String item_code, String start_date, String end_date, String format, String seq,String EnvName)
	{
		List<EcosRawLogAll> item =null;
		item =  serviceEcosAllData.find(stat_code, item_code, start_date, end_date);
		rconnector.makeDataFramefromRawData(name, item, start_date, end_date,format, seq, EnvName);
		return name;
	}
	
	@RequestMapping("/graph")
	ModelAndView graph(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("graph");
		return mav;
	}
	
	@RequestMapping("/")
	@ResponseBody
	Object index(Model model) throws RserveException, REXPMismatchException{

		//serviceEcosStatLog.insert("monthly","test");
		
		
		return serviceEcosStatLog.selectLastOne("monthly");
	}
	
}