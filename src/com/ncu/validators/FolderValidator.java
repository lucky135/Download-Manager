package com.ncu.validators;
import java.io.*;
import java.util.*;
import com.ncu.exceptions.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
public class FolderValidator
{
	public boolean folderValidator(String foldername)
	{
		Properties p=new Properties();
		FileInputStream input=null;
		boolean b;
		Logger logger= Logger.getLogger(FolderValidator.class);
		PropertyConfigurator.configure("C:\\Users\\laksh\\Desktop\\Download Manager\\configs\\logger\\logger.properties");
		try
		{
			input=new FileInputStream("C:\\Users\\laksh\\Desktop\\Download Manager\\configs\\constants\\Exceptions.properties");
			p.load(input);
			b=folderCheck(foldername);
		}
		catch(FolderNotException e){
			logger.error("\n \n"+e+p.getProperty("NoFolder"));
			return false;
		}
		catch(Exception e)
		{
			logger.error(e);
		}
		return true;
	}
 	boolean folderCheck(String foldername) throws FolderNotException
 	{
 		File file = new File(foldername);
 		if(file.exists())
 			return true;
 		else
 			throw new FolderNotException("");
 	}
}
