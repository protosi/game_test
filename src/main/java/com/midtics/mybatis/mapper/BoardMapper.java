package com.midtics.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.midtics.mybatis.domain.Board;

@Mapper
public interface BoardMapper {
	
	int insertBatch(List<Board> list);
	List<Board> select(@Param("offset") int offset, @Param("limit") int limit);
	Board selectById(@Param("id") String id);
	int selectCount();

}
