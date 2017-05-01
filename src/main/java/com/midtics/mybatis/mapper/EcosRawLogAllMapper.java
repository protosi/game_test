package com.midtics.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.midtics.mybatis.domain.EcosRawLogAll;

@Mapper
public interface EcosRawLogAllMapper {

	List<EcosRawLogAll> selectAll();
	int insertBatch(List<EcosRawLogAll> list);
}
