package com.midtics.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midtics.mybatis.domain.EcosRawLogAll;
import com.midtics.mybatis.mapper.EcosRawLogAllMapper;

@Service
public class EcosService {
	
	@Autowired
	EcosRawLogAllMapper mapper;
	
	public List<EcosRawLogAll> selectAll()
	{
		return mapper.selectAll();
	}
	public List<EcosRawLogAll> find(String stat_code,String item_code, String start_date, String end_date)
	{
		return mapper.find(stat_code, item_code, start_date, end_date);
	}
	public int insertBatch(List<EcosRawLogAll> list)
	{
		return mapper.insertBatch(list);
	}
}
