package CompareImages;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.imageio.ImageIO;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import Utils.LogsUtil;

public class Test extends Model{

	@org.testng.annotations.Test
	public void Testcase() throws Exception
	{
		LogsUtil.info("=====================================================================<<Excecutiong Start>>=====================================================================");
		Test obj=new Test();
		obj.ReadFromExcel();
		obj.openBrowser_n_takeScreenShot();
		LogsUtil.info("=====================================================================<<End of Execution>>=====================================================================");
	}
	
//	public static void main(String args[]) throws IOException
//	{
//		BufferedImage imageA=null;
//		BufferedImage imageB=null;
//		
//		File fileA=new File(".\\ScreenShots\\Expected.jpg");
//		File fileB=new File(".\\ScreenShots\\Actual.jpg");
//		
//		try{
//		imageA=ImageIO.read(fileA);
//		imageB=ImageIO.read(fileB);
//		}
//		catch(Exception e)
//		{
//			System.err.println(e.getMessage());
//		}
//		
//		int widthA=imageA.getWidth();
//		int widthB=imageB.getWidth();
//		int heightA=imageA.getHeight();
//		int heightB=imageB.getHeight();
//		
//		if(widthA!=widthB || heightA!=heightB)
//		{
//			System.out.println("Images Dimensions Are Mismatched");
//		}
//		BufferedImage OutputImage= new BufferedImage(widthA, heightA, BufferedImage.TYPE_INT_RGB);
//			int difference=0;
//			int result;
//			for(int y=0;y<heightA;y++)
//			{
//				for(int x=0;x<widthA;x++)
//				{
//					int rbgA=imageA.getRGB(x, y);
//					int rbgB=imageB.getRGB(x, y);
//					int redA=(rbgA >> 16) & 0xff;
//					int blueA=(rbgA >> 8) & 0xff;
//					int greenA=rbgA & 0xff;
//					int redB=(rbgB >> 16) & 0xff;
//					int blueB=(rbgA >> 8) & 0xff;
//					int greenB=rbgA & 0xff;
//					
//					difference+= Math.abs(redA-redB);	
//					difference+=Math.abs(greenA- greenB);
//					difference+=Math.abs(blueA-blueB);
//					
//					
//					//new line
//					difference/=3;
//					result=(difference<<16) | (difference<<8) | difference;
//					OutputImage.setRGB(x, y, result);
//			}
////			double total_pixels=widthA*heightA*3;
////			double avg_difference_pixels=difference/total_pixels;
////			double percentage=(avg_difference_pixels/255)*100;
////			System.out.println(percentage);
//		}
//			File OutputjpegFile=new File(".\\difference.jpg");
//			ImageIO.write(OutputImage, "jpg", OutputjpegFile);
//			System.out.println("Finish");
//			System.exit(0);
//	}
	
	@BeforeSuite
	public void BeforeSuite()
	{
		LogsUtil.info("=====================================================================<<Creating (Screen Shots) Directory Under Root Folder>>=====================================================================");
		File file=new File(System.getProperty("user.dir")+"\\ScreenShots");
		if(!file.exists())
			file.mkdir();	
	}
	
	@AfterSuite
	public void AfterSuite() throws IOException
	{			
	}
}
