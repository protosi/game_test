package com.midtics.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.midtics.mybatis.domain.EcosStatJobItem;

@Mapper
public interface EcosStatJobItemMapper {

	public List<EcosStatJobItem> selectY(@Param("category_id") int category_id);
}
