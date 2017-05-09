package com.midtics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midtics.mybatis.domain.Board;
import com.midtics.mybatis.mapper.BoardMapper;

@Service
public class BoardService {
	
	@Autowired
	BoardMapper boardMapper;
	
	public int insertBatch(List<Board> list)
	{
		return boardMapper.insertBatch(list);
	}
	public List<Board> select(int page, int list_size)
	{
		return boardMapper.select((page - 1) * list_size, list_size);
	}
	public Board selectById(String id)
	{
		return boardMapper.selectById(id);
	}
	
	public int selectCount()
	{
		return boardMapper.selectCount();
	}

}
