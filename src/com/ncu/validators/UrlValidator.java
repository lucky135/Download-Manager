package com.ncu.validators;
import com.ncu.exceptions.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
public class UrlValidator
{
	public boolean urlValidator(String url)
	{
		Properties p=new Properties();
		FileInputStream input=null;
		boolean b;
		Logger logger= Logger.getLogger(UrlValidator.class);
		PropertyConfigurator.configure("C:\\Users\\laksh\\Desktop\\Download Manager\\configs\\logger\\logger.properties");
		try
		{
			input=new FileInputStream("C:\\Users\\laksh\\Desktop\\Download Manager\\configs\\constants\\Exceptions.properties");
			p.load(input);
			b=backSlashPresent(url);
			b=spacePresent(url);
			b=httpPresent(url);
		}
		catch(BackSlashException e)
		{
			logger.error("\n \n"+e+p.getProperty("BackSlash"));
			return false;
		}
		catch(MissingSpaceException e)
		{
			logger.error("\n \n"+e+p.getProperty("SpacePresent"));
			return false;
		}
		catch(HttpMissingException e)
		{
			logger.error("\n \n"+e+p.getProperty("HttpMiss"));
			return false;
		}
		catch(Exception e)
		{
			logger.error(e);
		}

		return true;
	}
	boolean backSlashPresent(String url) throws BackSlashException
	{
		Pattern p=Pattern.compile("\\\\");
		Matcher m=p.matcher(url);
		boolean v=m.find();
		if(v==true)
		{
			throw new BackSlashException("");
		}
		return v;
	}
	boolean spacePresent(String url) throws MissingSpaceException
	{
		Pattern p=Pattern.compile(" ");
		Matcher m=p.matcher(url);
		boolean v=m.find();
		if(v==true)
		{
			throw new MissingSpaceException("");
		}
		return v;
	}
	boolean httpPresent(String url) throws HttpMissingException
	{
		boolean a,a1,a2=false,a3=false;
		
		String t1="https://";
		Pattern p=Pattern.compile(t1);
		Matcher m=p.matcher(url);
		a=m.find();

		String t2="http://";
		Pattern p1=Pattern.compile(t2);
		Matcher m1=p1.matcher(url);
		a1=m1.find();
		
		if(a1==false&&a==false)
		{
			throw new HttpMissingException("");
		}
		
		String[] ext=url.split(":");
		if(ext.length==2)
			a2=true;
		if(a2==false)
			throw new HttpMissingException("");

		String s1="https";
		String s2="http";
		if(ext[0].equals(s1))
			a3=true;
		if(ext[0].equals(s2))
			a3=true;
		if(a3==false)
			throw new HttpMissingException("");

		if(a1==true&&a2==true&&a3==true)
			return true;
		
		return false;
	}
}