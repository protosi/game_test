package com.midtics.mybatis.domain;

public class EcosStatJob {
	
	String name;
	String stat_code;
	String item_code;
	
	public EcosStatJob()
	{}
	
	public EcosStatJob(String name, String stat_code, String item_code)
	{
		this.name = name;
		this.stat_code = stat_code;
		this.item_code = item_code;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStat_code() {
		return stat_code;
	}
	public void setStat_code(String stat_code) {
		this.stat_code = stat_code;
	}
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

}
