package com.midtics.controller;

import java.util.Set;

import org.math.R.Rsession;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPGenericVector;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midtics.util.RConnector;

@RestController
public class TestController {
	
	
	
	@Autowired
	RConnector rconnector;
	
	@RequestMapping("/test1")
	public String test1() throws RserveException
	{
		String rt= "";
		rconnector.connect();
		rconnector.login();
		
		Rsession session = rconnector.getConnection();
		
		session.eval("x = 123");
		
		
		rconnector.close();
		return rt;
		
	}
	
	
	
	
	@RequestMapping("/test")
	public String test() throws RserveException, REXPMismatchException
	{
		String rt= "";
		rconnector.connect();
		rconnector.login();
		Rsession session = rconnector.getConnection();
		session.eval("require(vars)");
		session.eval("data(Canada)");
		session.eval("var.2c <- VAR(Canada, p = 2, type = \"const\")");
		
		REXP names = session.eval("colname(Canada)");
		
		
		
		REXP exp =  session.eval("causality(var.2c, cause = \"e\", vcov.=vcovHC(var.2c))");
		rconnector.close();
	
		RList list = exp.asList();
		rt = getList(list);
		
		return rt;
	}
	protected String getList(RList list)
	{
		String rt = "";
		
		
		Set<String> keys =  list.keySet();
		
		for(String key : keys)
		{
			if(list.get(key) instanceof REXPGenericVector)
			{
				try {
					rt += getList(((REXP)list.get(key)).asList());
				} catch (REXPMismatchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(list.get(key) instanceof RList)
			{
				rt += getList((RList)list.get(key));
			}
			else
			{
				try {
					rt += key + " : " + ((REXP)list.get(key)).asString() + "<br/>\n";
				} catch (REXPMismatchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return rt;
	}
}
