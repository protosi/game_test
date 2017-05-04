package com.midtics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midtics.mybatis.domain.EcosRawLogAll;
import com.midtics.mybatis.domain.EcosStatLog;
import com.midtics.mybatis.mapper.EcosRawLogAllMapper;
import com.midtics.mybatis.mapper.EcosStatLogMapper;

@Service
public class EcosRawLogService {
	
	@Autowired
	EcosRawLogAllMapper rawLogMapper;
	
	public List<EcosRawLogAll> selectAll()
	{
		return rawLogMapper.selectAll();
	}
	public List<EcosRawLogAll> find(String stat_code,String item_code, String start_date, String end_date)
	{
		return rawLogMapper.find(stat_code, item_code, start_date, end_date);
	}
	public int insertBatch(List<EcosRawLogAll> list)
	{
		return rawLogMapper.insertBatch(list);
	}
}
