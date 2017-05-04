package com.midtics.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.midtics.mybatis.domain.EcosStatJobCategory;

@Mapper
public interface EcosStatJobCategoryMapper {
	
	List <EcosStatJobCategory> selectY();

}
