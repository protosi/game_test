package com.midtics.util;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.math.R.RserverConf;
import org.math.R.Rsession;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.midtics.mybatis.domain.EcosRawLogAll;

@Component
public class RConnector {

	@Value("${R.host}")
	String Host;
	
	@Value("${R.port}")
	String Port;
	
	@Value("${R.id}")
	String Id;
	
	@Value("${R.password}")
	String Passwd;
	
	Rsession c = null;
	public RConnector()
	{
		//initialize();
	}

	public void initialize() throws RserveException
	{
		connect();
		login();
		String source = ResourceLoader.getInstance().getResource("RCode/exam.r");
		if(c.connected)
		{
			c.eval(source);
		}
	}
	public Rsession connect()
	{
		if(c == null)
		{
			c = Rsession.newInstanceTry(System.out,RserverConf.parse("R://"+Host+":"+Port));
			
		}
		else if(!c.connected)
		{

			c = Rsession.newInstanceTry(System.out,RserverConf.parse("R://"+Host+":"+Port));
		}
		return c;
	}
	public Rsession login() throws RserveException
	{
		if(c != null)
		{
			c.connection.login(Id, Passwd);
		}
		return c;
	}
	public Rsession close()
	{
		if(c != null)
		{
			c.close();
			c = null;
		}
		return c;
	}
	public Rsession getConnection()
	{
		return c;
	}
	public boolean assign(String varName,Object var, String envName)
	{
		String tempName = "temp" + System.nanoTime();
		c.set(tempName, var);
		c.eval("assign('"+varName+"', "+tempName+", envir='"+envName+"')");
		c.rm(tempName);
		
		return exists(varName, envName);
	}
	// R 환경에서 해당 변수가 있는지 확인하는 함수
	public boolean exists(String varName, String envName)
	{
		if(!c.connected)
		{
			return false;
		}
		try 
		{ 
			REXP result = c.eval("exists('"+varName+"', envir='"+envName+"')");
			if(result.asString().equals("TRUE"))
			{
				return true;
			}
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
		return false;
	}
	public boolean exists(String varName)
	{
		if(!c.connected)
		{
			return false;
		}
		try 
		{ 
			REXP result = c.eval("exists('"+varName+"')");
			if(result.asString().equals("TRUE"))
			{
				return true;
			}
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
		return false;
	}
	public void loadRSource()
	{
		if(c.connected)
		{
			String strRSource = ResourceLoader.getInstance().getResource("RCode/exam.r");
			c.eval(strRSource);
		}
	}
	
	public JSONObject forecastVAR(int p, String type,  int ahead, double ci, String envName, String...vars ) throws ParseException, JSONException, REXPMismatchException
	{
		String args = "";
		for(int i = 0 ; i < vars.length ; i++)
		{
			if(i != 0)
			{
				args += ", ";
			}
			//vars[i] = envName +"$"+ vars[i];
			args += envName +"$"+ vars[i]+"$value";
			
		}
		String rawName = envName +"$raw.df";
		String varName = envName + "$var";
		c.eval(rawName +"<-data.frame("+args+")");
		
		// predictVAR <- function(df, p, type, ahead, ci)
		c.eval(varName+" <-predictVAR(" +rawName+ ", "+ p +", '" +type+"'," + ahead+", " +ci+")");
		//c.toPNG(new File("e:/r/"+System.nanoTime()+".png"), 1024, 768, "plot("+varName+");");
		JSONObject obj = new JSONObject();
		for(int i = 0 ; i < vars.length ; i++)
		{
			JSONObject temp = new JSONObject();
			double[] raw = c.eval(envName +"$"+ vars[i]+"$value" ).asDoubles();
			double[] fcst = c.eval(varName+"$fcst$"+envName+"."+vars[i]+"[,'fcst']" ).asDoubles();
			double[] lower = c.eval(varName+"$fcst$"+envName+"."+vars[i]+"[,'lower']" ).asDoubles();
			double[] upper = c.eval(varName+"$fcst$"+envName+"."+vars[i]+"[,'upper']" ).asDoubles();
			String[] date = c.eval("as.factor("+envName +"$"+ vars[i]+"$date)").asStrings();
			
			JSONArray jsonFcst = new JSONArray();
			JSONArray jsonLower = new JSONArray();
			JSONArray jsonUpper = new JSONArray();
			JSONArray jsonDate = new JSONArray();
			for(int j = 0 ; j < raw.length ; j++)
			{
				jsonFcst.put(raw[j]);
				jsonLower.put(raw[j]);
				jsonUpper.put(raw[j]);
				jsonDate.put(date[j]);
			}
			
			Calendar _cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date _date = sdf.parse(date[raw.length-1]);
			_cal.setTime(_date);
			
			for(int j = 0 ; j < fcst.length ; j++)
			{
				jsonFcst.put(fcst[j]);
				jsonLower.put(lower[j]);
				jsonUpper.put(upper[j]);
				
				
				_cal.add(Calendar.MONTH, 1);
				jsonDate.put(sdf.format(_cal.getTime()));
				
			}
			//temp.put("raw", jsonRaw);
			temp.put("fcst", jsonFcst);
			temp.put("lower", jsonLower);
			temp.put("upper", jsonUpper);
			temp.put("date", jsonDate);
			obj.put(vars[i], temp);
		}
		
		return obj;
	}
	public boolean makeDataFramefromRawData(String var_name,List<EcosRawLogAll> lists, String start_date, String end_date, String date_format, String seq, String envName)
	{
		String[] date = new String[lists.size()];
		String[] value = new String[lists.size()];
		
		// 환경이 없는 경우에는 해당 환경을 만든다.
		if(!exists(envName))
		{
			c.eval(envName + "<-new.env()");
		}
		
		for(int i = 0 ; i < lists.size() ; i++)
		{
			date[i] = lists.get(i).getTIME();
			value[i] = lists.get(i).getDATA_VALUE();
		}
		return makeDataFrame(var_name, value, date, start_date, end_date, date_format, seq, envName);
	}
	public boolean makeDataFrame(String varName, String[] value, String[] date, String startDate, String endDate, String dateFormat, String seq, String envName)
	{
		try
		{
			if(date.length == 0 )
			{	
				return false;
			}
			if(!c.connected)
			{
				return false;
			}
			// 환경이 없는 경우에는 해당 환경을 만든다.
			if(!exists(envName))
			{
				c.eval(envName + "<-new.env()");
			}
			String tempName = "temp" + System.nanoTime();
			
			String tempValue = tempName +".value";
			String tempDate = tempName +".date";
			String tempDF = tempName +".df";
			// 전달 받은 난수를 temp 이름으로 할당
			c.set(tempValue, value);
			c.set(tempDate, date);
			
			if(seq.contains("month"))
			{
				c.eval(tempDate + "<-paste("+tempDate+ ", '01', sep='' )");
				startDate += "01";
				endDate += "01";
			}
			
			// tempDF를 만든다.
			//makeDataFrame <- function(date, value , startDate, endDate, dateFormat, seq )
			c.eval(envName+"$"+ varName+"<-makeDataFrame("+ tempDate+", " + tempValue+", '" + startDate+"', '" + endDate + "', '"
			+dateFormat+"',  '" +seq+ "')");
	
			
			
			// 임시로 만든 변수들을 해제한다.
			c.rm(tempValue, tempDate);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
		
	}
}
