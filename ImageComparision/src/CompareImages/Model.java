package CompareImages;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.junit.rules.Timeout;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.ExcelReadWrite;
import Utils.LogsUtil;

public class Model extends ExcelReadWrite{
	private static Model instance=null;
	private String TestBrowser="Chrome";
	private static WebDriver driver;

	public static Model getInstance()
	{
		if(instance==null)
			instance=new Model();
		return instance;
	}
	
	public void openBrowser_n_takeScreenShot() throws Exception
	{
		if(TestBrowser.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
			LogsUtil.info("=====================================================================<<Opening Web Browser Instance>>=====================================================================");
			driver =new ChromeDriver();
		}
		else
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\geckodriver.exe");
			LogsUtil.info("=====================================================================<<Opening Web Browser Instance>>=====================================================================");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		String pathofExpectedImage=null;
		String pathofActualImage=null;
		double diff;
		for(int i=0;i<ExpectedURLs.size();i++)
		{
			//System.out.println(ExpectedURLs);
			LogsUtil.info("=====================================================================<<Executing S.No = ("+ (i+1) +") Case from Sheet >>=====================================================================");
			LogsUtil.info("<<Creating Test Case ("+ (i+1) +") Dirctory under Screen Shot Folder>>");
			new File(".\\ScreenShots\\"+(i+1)).mkdir();
			try{
			driver.get(ExpectedURLs.get(i).trim());
			}
			catch(Exception e)
			{
				LogsUtil.error("Error Occured while fetching Expected URL from Array List, Exception Trace =" + e.getMessage());
			}
			pathofExpectedImage=getInstance().CaptureScreen("Expected", (i+1));
			
			
			driver.get(ActualURLs.get(i).toString());
			pathofActualImage=getInstance().CaptureScreen("Actual", (i+1));
			
			diff=getInstance().compare(pathofExpectedImage,pathofActualImage, (i+1));
			if(diff==0.0)
			{
				getInstance().Result.add("Pass");
				getInstance().Difference.add(diff);
			}
			else
			{
				getInstance().Result.add("Fail");
				getInstance().Difference.add(diff);
			}
			LogsUtil.info("Test Result =" + getInstance().Result.get(i));
			LogsUtil.info("=====================================================================<<End of S.No = ("+ (i+1) +") Case>>=====================================================================");
		}
		getInstance().WriteonExcel();
		LogsUtil.info("=====================================================================<<Closing Web Browser Instance>>=====================================================================");
		driver.close();
	}
	public String CaptureScreen(String Run, int Sno) throws Exception
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		if(Run.equals("Expected"))
		{
		try{
			LogsUtil.info("Saving Expected URL Screen Shot");
		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "\\ScreenShots\\" + Sno +"\\Expected.jpg"));
		return (System.getProperty("user.dir")+"\\ScreenShots\\" + Sno +"\\Expected.jpg");
		}
		catch(Exception e)
		{
			LogsUtil.error("Error Occured While Capturing Expected URL Screen Shot, Exception Trace = "+e.getMessage());
			return (System.getProperty("user.dir")+"\\ScreenShots\\" + Sno +"\\Expected.jpg");
		}
		}
		else
		{
			try{
				LogsUtil.info("Saving Actual URL Screen Shot");
				FileUtils.copyFile(src, new File(".\\ScreenShots\\" + Sno +"\\Actual.jpg"));
				return (System.getProperty("user.dir")+"\\ScreenShots\\" + Sno +"\\Actual.jpg");
				}
				catch(Exception e)
				{
					LogsUtil.error("Error Occured While Capturing Actual URL Screen Shot, Exception Trace = "+e.getMessage());
					return (System.getProperty("user.dir")+"\\ScreenShots\\" + Sno +"\\Actual.jpg");
				}
		}
	}
	
	public double compare(String Expected, String Actual, int Sno) throws IOException
	{
		BufferedImage imageA=null;
		BufferedImage imageB=null;
		
		File fileA=new File(Expected);
		File fileB=new File(Actual);
		
		imageA=ImageIO.read(fileA);
		imageB=ImageIO.read(fileB);
				
		int widthA=imageA.getWidth();
		int widthB=imageB.getWidth();
		int heightA=imageA.getHeight();
		int heightB=imageB.getHeight();
		
//		if(widthA!=widthB || heightA!=heightB)
//		{
//			System.out.println("Images Dimensions Are Mismatched");
//		}
//		else
//		{
		BufferedImage OutputImage= new BufferedImage(widthA, heightA, BufferedImage.TYPE_INT_RGB);
		int result,resultdifference=0;
		long difference=0;
			for(int y=0;y<heightA;y++)
			{
				for(int x=0;x<widthA;x++)
				{
					int rbgA=imageA.getRGB(x, y);
					int rbgB=imageB.getRGB(x, y);
					int redA=(rbgA >> 16) & 0xff;
					int blueA=(rbgA >> 8) & 0xff;
					int greenA=rbgA & 0xff;
					int redB=(rbgB >> 16) & 0xff;
					int blueB=(rbgA >> 8) & 0xff;
					int greenB=rbgA & 0xff;
					
					difference+= Math.abs(redA-redB);
					difference+=Math.abs(greenA- greenB);
					difference+=Math.abs(blueA-blueB);
					/////////////////////////////////////////////////////Code for New difference Image Only that requirest operation with INT Datatype
					resultdifference+= Math.abs(redA-redB);
					resultdifference+=Math.abs(greenA- greenB);
					resultdifference+=Math.abs(blueA-blueB);
					resultdifference/=3;
					result=(resultdifference<<16) | (resultdifference<<8) | resultdifference;
					OutputImage.setRGB(x, y, (int)result);
				}
			}
			double total_pixels=widthA*heightA*3;
			double avg_difference_pixels=difference/total_pixels;
			double percentage=(avg_difference_pixels/255)*100;
			LogsUtil.info("Mismatched Percentage at the Time of Comparision = " + percentage);
			
			if(percentage!=0.0)
			{
				LogsUtil.info("Creating Differnce File under Test Case/S.No Case Folder");
				File OutputjpegFile=new File(System.getProperty("user.dir")+ "\\ScreenShots\\" + Sno +"\\Difference.jpg");
				ImageIO.write(OutputImage, "jpg", OutputjpegFile);
			}
			return percentage;
			
		//}
	}
	
}
