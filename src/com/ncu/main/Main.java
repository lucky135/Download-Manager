import com.ncu.validators.*;
import com.ncu.processors.*;
import java.util.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
class Main
{
	public static void main(String[] args)
	{
		int count=0,x=0;
		System.out.println("\n\nWelcome To The Project Made by Team B (CSE B)\nDOWNLOAD MANAGER");
		do
		{
			Logger logger= Logger.getLogger(Main.class);
			PropertyConfigurator.configure("C:\\Users\\laksh\\Desktop\\Download Manager\\configs\\logger\\logger.properties");
			Scanner con=new Scanner(System.in);

			String path="no",link="no";
			boolean b,v;
			
			do
			{
				x=0;
				System.out.print("\nEnter Download Link : ");
				String lk=con.nextLine();
				UrlValidator a1=new UrlValidator();
				b=a1.urlValidator(lk);
				if(b==false)
					x=1;
				else
					link=lk;
			}while(x==1);
			do
			{
				x=0;
				System.out.print("\nEnter Output Folder : ");
				String pt=con.nextLine();
				FolderValidator t=new FolderValidator();
				v=t.folderValidator(pt);
				if(v==false)
					x=1;
				else
					path=pt;
			}while(x==1);
			count++;
			if(b==true&&v==true)
			{
				FileDownloader arr=new FileDownloader();
				double percent=arr.fileDownloader(link,path,count);
				StatusWriter a=new StatusWriter();
				a.statusWriter(link,percent,count);
			}
			System.out.print("Want to Download Another File?\tPress 1 -- ");
		    x=con.nextInt();
			con.nextLine();
		}while(x==1);
		System.out.println("\nThank You for using Our Software.");
	}
}