package com.midtics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midtics.mybatis.domain.EcosStatLog;
import com.midtics.mybatis.mapper.EcosStatLogMapper;

@Service
public class EcosStatLogService {
	
	@Autowired
	EcosStatLogMapper statLogMapper;
	
	public EcosStatLog selectLastOne(String category)
	{
		return statLogMapper.selectLastOne(category);
	}
	public EcosStatLog selectLastOneUntilDate(String category, String date)
	{
		return null;
	}
	public int insert(String category, String value)
	{
		return statLogMapper.insert(category, value);
	}

}
