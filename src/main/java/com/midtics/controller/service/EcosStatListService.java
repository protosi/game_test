package com.midtics.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midtics.mybatis.domain.EcosStatList;
import com.midtics.mybatis.mapper.EcosStatListMapper;

@Service
public class EcosStatListService {
	
	@Autowired
	EcosStatListMapper statListMapper;
	
	public List<EcosStatList> selectY()
	{
		return statListMapper.selectY();
	}
	
	public int insert(String stat_code, String stat_name, String cycle)
	{
		return statListMapper.insert(stat_code, stat_name, cycle);
	}

}
