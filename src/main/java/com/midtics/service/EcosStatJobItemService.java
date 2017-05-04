package com.midtics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midtics.mybatis.domain.EcosStatJobItem;
import com.midtics.mybatis.mapper.EcosStatJobItemMapper;

@Service
public class EcosStatJobItemService {
	
	@Autowired
	EcosStatJobItemMapper mapper;
	
	public List<EcosStatJobItem> selectY(int category_id)
	{
		return mapper.selectY(category_id);
	}

}
