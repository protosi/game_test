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
	public List<Board> select()
	{
		return boardMapper.select();
	}
	public int selectCount()
	{
		return boardMapper.selectCount();
	}

}
