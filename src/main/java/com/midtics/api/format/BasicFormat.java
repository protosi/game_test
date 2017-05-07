package com.midtics.api.format;

public class BasicFormat <T>{
	
	Result result;
	T recv;
	
	public BasicFormat()
	{
		super();
		result = new Result();
	}
	public BasicFormat(int code, String msg)
	{
		super();
		result = new Result();
		setCode(code);
		setMsg(msg);
	}
	public BasicFormat(int code, String msg, T obj)
	{
		super();
		result = new Result();
		setCode(code);
		setMsg(msg);
		recv = obj;
	}
	
	public void setResult(int code, String msg)
	{
		setCode(code);
		setMsg(msg);
	}
	public void setCode(int code)
	{
		result.setCode(code);
	}
	
	public void setMsg(String msg)
	{
		result.setMsg(msg);
	}
	
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public T getRecv() {
		return recv;
	}
	public void setRecv(T recv) {
		this.recv = recv;
	}

}
