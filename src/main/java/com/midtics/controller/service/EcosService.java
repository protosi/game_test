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

}
