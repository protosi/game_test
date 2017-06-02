package com.midtics.component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.midtics.mybatis.domain.EcosRawLogAll;
import com.midtics.mybatis.domain.EcosStatJob;
import com.midtics.mybatis.domain.EcosStatJobCategory;
import com.midtics.mybatis.domain.EcosStatJobItem;
import com.midtics.service.EcosRawLogService;
import com.midtics.service.EcosStatJobCategoryService;
import com.midtics.service.EcosStatJobItemService;
import com.midtics.service.EcosStatLogService;
import com.midtics.util.RConnector;

@Component
public class EcosStatJobComponent implements Runnable{
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	EcosRawLogService serviceEcosAllData;
	
	@Autowired
	EcosStatLogService serviceEcosStatLog;
	
	@Autowired
	EcosStatJobCategoryService serviceEcosJobCategory;
	
	@Autowired
	EcosStatJobItemService serviceEcosJobItem;
	
	@Autowired
	RConnector rconnector;
	
	public void logJob(String Category, List<EcosStatJobItem> joblist, String Cycle, String start_date)
	{
		SimpleDateFormat sdf = null;
		String step = "";
		int season = 0;
		try {
			rconnector.initialize();
		} catch (RserveException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(Cycle.equals("MM"))
		{
			sdf = new SimpleDateFormat("yyyyMM");
			season = 12;
			step = "1 month";
		}
		else if(Cycle.equals("DD"))
		{
			sdf = new SimpleDateFormat("yyyyMMdd");
			step = "1 day";
			season = 7;
		}
		Date _date = new Date();
		String end_date = sdf.format(_date);
		
		String envName = "temp" +System.nanoTime();
		List<String> name = new ArrayList<String>();
		
		for(EcosStatJobItem job : joblist)
		{
			name.add(makeDataFramefromRawData(job.getName(), job.getStat_code(), job.getItem_code(), start_date, end_date, "%Y%m%d", step, envName));
			
			
		}
		
		String[] nameArray = new String[name.size()];
		JSONObject json = null;
		try {
			//json= rconnector.predictVAR(10, "both", 12, 0.60, envName, Cycle, name.toArray(nameArray));
			
			json =  rconnector.predictVECM("trend", "eigen", 3, season, 12, 0.6, envName, Cycle, name.toArray(nameArray));
			
			serviceEcosStatLog.insert(Category,json.toString());
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
	}
	
	public void LogJobList()
	{
		List<EcosStatJobCategory> categorylist = serviceEcosJobCategory.selectY();
		
		for(EcosStatJobCategory category : categorylist)
		{
			List<EcosStatJobItem> itemlist = serviceEcosJobItem.selectY(category.getId());
			
			logJob(category.getCategory(), itemlist , category.getCycle(), category.getStart_date());
		}
	}
	
	
	protected String  makeDataFramefromRawData(String name, String stat_code, String item_code, String start_date, String end_date, String format, String seq,String EnvName)
	{
		List<EcosRawLogAll> item = serviceEcosAllData.find(stat_code, item_code, start_date, end_date);
		rconnector.makeDataFramefromRawData(name, item, start_date, end_date,format, seq, EnvName);
		return name;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		LogJobList();
	}

}
