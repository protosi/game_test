package com.midtics.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.midtics.mybatis.domain.EcosStatLog;

@Mapper
public interface EcosStatLogMapper {
	
	public EcosStatLog selectLastOne(@Param("category") String category);
	public int insert(@Param("category") String category, @Param("value") String value);

}
