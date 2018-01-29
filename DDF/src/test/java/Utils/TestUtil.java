package Utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import Model.Keywords;

public class TestUtil {

	public static String SCFileName=null;
	public static void CaptureScreenShot() throws IOException
	{
		Date d=new Date();
		SCFileName = d.toString().replace(" ", "_").replace(":", "_") + ".jpg";
		
		File scr=((TakesScreenshot) Keywords.driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scr, new File(System.getProperty("user.dir")+ "\\HTMLOutput\\" + SCFileName));	
		FileUtils.copyFile(scr, new File(System.getProperty("user.dir")+ "\\test-output\\html\\" + SCFileName));
		
	}
}
