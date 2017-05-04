package com.midtics.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.midtics.mybatis.domain.EcosRawLogAll;

@Mapper
public interface EcosRawLogAllMapper {

	List<EcosRawLogAll> selectAll();
	List<EcosRawLogAll> find(@Param("stat_code") String stat_code, @Param("item_code") String item_code, 
			@Param("start_date") String start_date, @Param("end_date") String end_date);
	int insertBatch(List<EcosRawLogAll> list);
}
