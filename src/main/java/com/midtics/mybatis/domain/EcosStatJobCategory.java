package com.midtics.mybatis.domain;

public class EcosStatJobCategory {
	
	int id;
	String category;
	String cycle;
	String is_active;
	String start_date;
	String stat_function;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getIs_active() {
		return is_active;
	}
	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getStat_function() {
		return stat_function;
	}
	public void setStat_function(String stat_function) {
		this.stat_function = stat_function;
	}
}
