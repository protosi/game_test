package com.midtics.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.midtics.controller.service.EcosRawLogService;
import com.midtics.controller.service.EcosStatListService;
import com.midtics.mybatis.domain.EcosRawLogAll;
import com.midtics.mybatis.domain.EcosStatList;

@Component
public class BOKUploader implements Runnable {
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	EcosStatListService serviceEcosStatList;
	
	@Autowired
	EcosRawLogService serviceEcosRawLog;
	
	List <EcosStatList> apiList = null;
	
	
	
	
	

	
	
	public void insertEcosStatList() throws JSONException
	{
		String json = BOKConnector.getInstance().getEcosStat();
		JSONObject apiList = new JSONObject(json);
		if(!apiList.has("StatisticTableList"))
		{
			return;
		}
		if(!apiList.getJSONObject("StatisticTableList").has("row"))
		{
			return;
		}
		
		JSONArray rows = apiList.getJSONObject("StatisticTableList").getJSONArray("row");
		

		for(int i = 0 ; i < rows.length() ; i++)
		{
			if(rows.getJSONObject(i).has("SRCH_YN"))
			{
				if(rows.getJSONObject(i).getString("SRCH_YN").equals("Y"))
				{
					if(rows.getJSONObject(i).has("STAT_CODE") && rows.getJSONObject(i).has("CYCLE") && rows.getJSONObject(i).has("STAT_NAME"))
					{
						String statCode = rows.getJSONObject(i).getString("STAT_CODE");
						String statName = rows.getJSONObject(i).getString("STAT_NAME");
						String cycle = rows.getJSONObject(i).getString("CYCLE");
						serviceEcosStatList.insert(statCode, statName, cycle);
					}
				}
			}
		}
		
	}
	
	
	
	public void loadApiStat(String stat_code, String cycle)
	{
		try {
			processItemList(stat_code, cycle);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void loadApiStatAll()
	{
		apiList = serviceEcosStatList.selectY();
		
		try {
			processApiListAll();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void processApiListAll() throws JSONException
	{

		if(apiList == null)
		{
			return;
		}
		
		for(int i = 0 ; i < apiList.size() ; i++)
		{
			String statCode = apiList.get(i).getSTAT_CODE();
			String cycle = apiList.get(i).getCYCLE();
						
			try
			{
				processItemList(statCode,cycle);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
	protected void processItemList(String statCode, String cycle) throws JSONException
	{
		String json = BOKConnector.getInstance().getEcodeItemCode(statCode);

		JSONObject obj = new JSONObject(json);
		if(!obj.has("StatisticItemList"))
		{
			return;
		}
		JSONArray array = obj.getJSONObject("StatisticItemList").getJSONArray("row");

		
		
		for(int j = 0 ; j < array.length() ; j++)
		{
			try{
			processStatList(statCode,cycle, array.getJSONObject(j).getString("ITEM_CODE"));	
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	protected void processStatList(String statCode, String cycle, String itemcode)throws JSONException
	{
		Calendar now = Calendar.getInstance();
		String from_year = "1900";
		String to_year = String.valueOf(now.get(Calendar.YEAR)); 
		String json = "";
		if(cycle.equals("DD"))
		{
			json = BOKConnector.getInstance().getEcosCycleData(statCode, cycle, from_year +"0101", to_year+"1231", itemcode, "?", "?"); 
		}
		if(cycle.equals("MM"))
		{
			json = BOKConnector.getInstance().getEcosCycleData(statCode, cycle, from_year +"01", to_year+"12", itemcode, "?", "?"); 
		}
		if(cycle.equals("HM"))
		{
			json = BOKConnector.getInstance().getEcosCycleData(statCode, cycle, from_year +"011", to_year+"122", itemcode, "?", "?"); 
		}
		if(cycle.equals("QQ"))
		{
			json = BOKConnector.getInstance().getEcosCycleData(statCode, cycle, from_year +"1", to_year+ "4", itemcode, "?", "?"); 
		}
		if(cycle.equals("YY"))
		{
			json = BOKConnector.getInstance().getEcosCycleData(statCode, cycle, from_year , to_year, itemcode, "?", "?"); 
		}
		try
		{
			logger.debug(statCode + " / " + itemcode + " / " + cycle);
			saveJsonToDatabase(json , cycle, itemcode);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void saveJsonToDatabase(String json, String cycle, String itemcode) throws JSONException
	{
		JSONObject obj = null;
		String strTime = "";
		String strItemname = "";
		obj = new JSONObject(json);
		if(!obj.has("StatisticSearch"))
		{
			System.out.println("no StatisticSearch");
			return;
		}
		JSONArray array = obj.getJSONObject("StatisticSearch").getJSONArray("row");
		String curr_data = "";
		String itemname1 = "";
		String itemname2 = "";
		String itemname3 = "";
		String itemname = "";
		String unitname = "";
		String statcode = "";
		String statname = "";
		List <EcosRawLogAll> list = new ArrayList<EcosRawLogAll>();
		for(int i = 0 ; i < array.length() ; i++)
		{
			JSONObject jobj = ((JSONObject)array.get(i));
			curr_data = jobj.getString("DATA_VALUE");
			strTime = jobj.getString("TIME");
			
			if(jobj.has("ITEM_NAME1"))
			{
				itemname1 = jobj.getString("ITEM_NAME1");
			}
			if(jobj.has("ITEM_NAME2"))
			{
				itemname2 = jobj.getString("ITEM_NAME2");
			}
			if(jobj.has("ITEM_NAME3"))
			{
				itemname3 = jobj.getString("ITEM_NAME3");
			}
			if(jobj.has("ITEM_NAME"))
			{
				itemname = jobj.getString("ITEM_NAME");
			}
			if(jobj.has("UNIT_NAME"))
			{
				unitname = jobj.getString("UNIT_NAME");
			}
			if(jobj.has("UNIT_NAME"))
			{
				unitname = jobj.getString("UNIT_NAME");
			}
			if(jobj.has("STAT_CODE"))
			{
				statcode = jobj.getString("STAT_CODE");
			}
			if(jobj.has("STAT_NAME"))
			{
				statname = jobj.getString("STAT_NAME");
			}
			
			list.add(new EcosRawLogAll(statcode, itemcode, strTime, statname, itemname1, itemname2, itemname3, itemname, unitname, cycle, curr_data));
		}
		//logger.debug(statcode + "(" + statname +") : " + itemname +" / "+ itemname1 +" / "+  itemname2 +" / "+ itemname3  +" / "+unitname +" / "+ cycle +" / "+list.size());
		serviceEcosRawLog.insertBatch(list);
			
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		loadApiStatAll();
	}
}
