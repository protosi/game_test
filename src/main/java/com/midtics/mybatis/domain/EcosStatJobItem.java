package com.midtics.mybatis.domain;

public class EcosStatJobItem {
	
	int id;
	int category_id;
	String name;
	String stat_code;
	String item_code;
	String is_active;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
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
	public String getIs_active() {
		return is_active;
	}
	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}
}
