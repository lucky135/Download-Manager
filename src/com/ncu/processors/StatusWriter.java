package com.ncu.processors;
import java.io.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
public class StatusWriter
{
	public void statusWriter(String link,double percent,int count)
	{
		Logger logger= Logger.getLogger(StatusWriter.class);
		PropertyConfigurator.configure("C:\\Users\\laksh\\Desktop\\Download Manager\\configs\\logger\\logger.properties");
		try
		{
			File file = new File("C:\\Users\\laksh\\Desktop\\Download Manager\\status\\Status.txt");
			FileWriter fr = new FileWriter(file, true);
			BufferedWriter br = new BufferedWriter(fr);
			br.write("Download link : "+link);
			br.newLine();
			br.write("File Name : file"+count);
			br.newLine();
			if(percent==100)
				br.write("Status : Download complete.");
			else
				br.write("Status : Download failed.");
			br.newLine();
			br.newLine();
			br.close();
			fr.close();
			System.out.println("\nWritten in Status\n");
		}
		catch(Exception e)
		{
			logger.error(e);
		}
	}
}