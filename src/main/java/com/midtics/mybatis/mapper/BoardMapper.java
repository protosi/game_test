package com.midtics.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.midtics.mybatis.domain.Board;

@Mapper
public interface BoardMapper {
	
	int insertBatch(List<Board> list);

}
