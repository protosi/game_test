package com.midtics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midtics.mybatis.domain.EcosStatJobCategory;
import com.midtics.mybatis.mapper.EcosStatJobCategoryMapper;

@Service
public class EcosStatJobCategoryService {
	
	@Autowired
	EcosStatJobCategoryMapper mapper;
	
	public List<EcosStatJobCategory> selectY()
	{
		return mapper.selectY();
	}

}
