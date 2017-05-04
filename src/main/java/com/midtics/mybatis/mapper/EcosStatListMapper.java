package com.midtics.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.midtics.mybatis.domain.EcosStatList;

@Mapper
public interface EcosStatListMapper {
	
	public List<EcosStatList> selectY();
	
	public int insert(@Param("stat_code") String stat_code, @Param("stat_name") String stat_name, @Param("cycle") String cycle);

}
