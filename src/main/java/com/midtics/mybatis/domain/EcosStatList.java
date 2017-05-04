package com.midtics.mybatis.domain;

import org.springframework.stereotype.Service;


public class EcosStatList {
	
	int id;
	String STAT_CODE;
	String STAT_NAME;
	String CYCLE;
	String is_store;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSTAT_CODE() {
		return STAT_CODE;
	}
	public void setSTAT_CODE(String sTAT_CODE) {
		STAT_CODE = sTAT_CODE;
	}
	public String getSTAT_NAME() {
		return STAT_NAME;
	}
	public void setSTAT_NAME(String sTAT_NAME) {
		STAT_NAME = sTAT_NAME;
	}
	public String getCYCLE() {
		return CYCLE;
	}
	public void setCYCLE(String cYCLE) {
		CYCLE = cYCLE;
	}
	public String getIs_store() {
		return is_store;
	}
	public void setIs_store(String is_store) {
		this.is_store = is_store;
	}
}
