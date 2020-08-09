package com.ncu.processors;
import java.io.*;
import java.util.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
public class FileDownloader
{
	public double fileDownloader(String link,String path,int count)
	{
        double percentDownload = 0.00;
        Logger logger= Logger.getLogger(FileDownloader.class);
        PropertyConfigurator.configure("C:\\Users\\laksh\\Desktop\\Download Manager\\configs\\logger\\logger.properties");
		try 
  		{
  			String str=link;
  			URL url = new URL(link);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            double fileSize = (double) http.getContentLengthLong();
  			BufferedInputStream in = new BufferedInputStream(new URL(link).openStream());
    		String[] s=str.split("[.]");
    		int size=s.length;
    		String ext=s[size-1];
    		String p=(path+"\\file"+count+"."+ext);
    		FileOutputStream fileOutputStream = new FileOutputStream(p);
    		byte dataBuffer[] = new byte[1024];
    		int bytesRead=0;
    		double download = 0.00;
    		while ((bytesRead = in.read(dataBuffer, 0, 1024)) >= 0) 
    		{
        		fileOutputStream.write(dataBuffer, 0, bytesRead);
        		download += bytesRead;
                percentDownload=(download*100)/fileSize;
                String percent = String.format("%.4f",percentDownload);
                System.out.println("Downloaded "+percent+"% of the file.");
   			}
   			in.close();
            if(percentDownload==100)
                System.out.println("\nDOWNLOAD COMPLETE\n");
		} 
		catch (IOException e) 
        {
    		logger.error(e);
		}
        return percentDownload;
	}
}