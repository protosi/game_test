package com.midtics.util;

import java.io.IOException;

import org.apache.http.client.fluent.Request;

public class BOKConnector {
	
	private static final BOKConnector mInstance = new BOKConnector();
	
	private final String ecos_data_url = "http://ecos.bok.or.kr/api/StatisticSearch/#api_key#/json/en/1/99999/#api_code#/DD/#start_date#/#end_date#/#option1#/#option2#/#option3#/";
	private final String ecos_cycle_data_url = "http://ecos.bok.or.kr/api/StatisticSearch/#api_key#/json/kr/1/99999/#api_code#/#cycle#/#start_date#/#end_date#/#option1#/#option2#/#option3#/";
	private final String ecos_item_code_url = "http://ecos.bok.or.kr/api/StatisticItemList/QG92ECHKUNXASSDU9ZBH/json/kr/1/99999/#api_code#/";
	private final String ecos_stat_url = "http://ecos.bok.or.kr/api/StatisticTableList/#api_key#/json/kr/1/99999/#option1#/";
	private final String api_key = "QG92ECHKUNXASSDU9ZBH"; 
	
	protected BOKConnector()
	{}
	
	public static BOKConnector getInstance()
	{
		return mInstance;
	}
	
	public String getApiKey()
	{
		return api_key;
	}
	public String getEcosData(String api_code, String startDate, String endDate, String option1, String option2, String option3)
	{
		String url = getEcosDataApiUrl(api_code, startDate, endDate, option1,option2, option3);
		String json = "";
		
		try {
			json = Request.Get(url).execute().returnContent().asString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			json = "{}";
		}
		
		return json;
	}
	public String getEcosCycleData(String api_code, String cycle, String startDate, String endDate, String option1, String option2, String option3)
	{
		String url = getEcosCycleDataApiUrl(api_code, cycle, startDate, endDate, option1,option2, option3);
		String json = "";
		
		try {
			json = Request.Get(url).execute().returnContent().asString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			json = "{}";
		}
		//System.out.println(json);
		return json;
	}
	public String getEcosData(String api_code, String startDate, String endDate)
	{
		String url = getEcosDataApiUrl(api_code, startDate, endDate, "?", "?", "?");
		String json = "";
		
		try {
			json = Request.Get(url).execute().returnContent().asString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			json = "{}";
		}
		
		return json;
	}
	public String getEcosCycleData(String api_code,String cycle, String startDate, String endDate)
	{
		String url = getEcosCycleDataApiUrl(api_code, cycle, startDate, endDate, "?", "?", "?");
		String json = "";
		
		try {
			json = Request.Get(url).execute().returnContent().asString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			json = "{}";
		}
		
		return json;
	}
	public String getEcosStat()
	{
		String url = getEcosStatApiUrl("?");
		String json = "";
		try {
			json = Request.Get(url).execute().returnContent().asString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			json = "{}";
		}
		
		return json;
	}
	public String getEcodeItemCode(String api_code)
	{
		String url = getEcosItemCodeApiUrl(api_code);
		String json = "";
		try {
			json = Request.Get(url).execute().returnContent().asString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			json = "{}";
		}
		//System.out.println(json);
		return json;
	}
	protected String getEcosStatApiUrl(String option1)
	{
		String url = ecos_stat_url.replace("#api_key#", api_key)
				.replace("#option1#", option1);
		return url;
	}
	protected String getEcosItemCodeApiUrl(String api_code)
	{
		String url = ecos_item_code_url.replace("#api_code#", api_code).replace("#api_key#", api_key);
		return url;
	}
	protected String getEcosCycleDataApiUrl(String api_code, String cycle, String startDate, String endDate, String option1, String option2, String option3)
	{
		String url = ecos_cycle_data_url.replace("#api_key#", api_key)
				.replace("#api_code#", api_code)
				.replace("#option1#", option1)
				.replace("#option2#", option2)
				.replace("#option3#", option3)
				.replace("#cycle#", cycle)
				.replace("#start_date#", startDate.replaceAll("-", ""))
				.replace("#end_date#", endDate.replaceAll("-", ""));
		return url;
	}
	protected String getEcosDataApiUrl(String api_code, String startDate, String endDate, String option1, String option2, String option3)
	{
		String url = ecos_data_url.replace("#api_key#", api_key)
				.replace("#api_code#", api_code)
				.replace("#option1#", option1)
				.replace("#option2#", option2)
				.replace("#option3#", option3)
				.replace("#start_date#", startDate.replaceAll("-", ""))
				.replace("#end_date#", endDate.replaceAll("-", ""));
		return url;
	}
	

}
