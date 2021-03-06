package com.midtics.api.format;

import java.util.List;

public class ListFormat <T>{
	
	Result result = new Result();
	ListRecv<T> recv = new ListRecv<T>();
	
	public ListFormat()
	{
		super();
	}
	public ListFormat(int code, String msg)
	{
		super();
		setCode(code);
		setMsg(msg);
	}
	public ListFormat(int code, String msg, List<T> list, int list_size, int page, int total)
	{
		super();
		setCode(code);
		setMsg(msg);
		recv.setList(list);
		recv.setPage(page);
		recv.setTotal(total);
		recv.setList_size(list_size);
	}
	public void setList(List<T> list, int list_size, int page, int total)
	{
		recv.setList(list);
		recv.setPage(page);
		recv.setTotal(total);
		recv.setList_size(list_size);
	}
	public void setPage(int page)
	{
		recv.setPage(page);
	}
	public void setList(List<T> list)
	{
		recv.setList(list);
	}
	public void setCode(int code)
	{
		result.setCode(code);
	}
	
	public void setMsg(String msg)
	{
		result.setMsg(msg);
	}
	public ListRecv<T> getRecv()
	{
		return recv;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
}
