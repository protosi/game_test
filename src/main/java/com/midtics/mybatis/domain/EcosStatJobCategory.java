package com.midtics.mybatis.domain;

public class EcosStatJobCategory {
	
	int id;
	String category;
	String Cycle;
	String is_active;
	String start_date;
	
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
		return Cycle;
	}
	public void setCycle(String cycle) {
		Cycle = cycle;
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
	
}
