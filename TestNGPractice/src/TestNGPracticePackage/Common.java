package TestNGPracticePackage;

import java.io.IOException;
import java.security.PrivateKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sun.jna.platform.win32.Winioctl.STORAGE_DEVICE_NUMBER;

import Utils.LogUtil;

public class Common {
	static String browser=null;
	static Common instance=null;
	WebDriver driver=null;
	static String productbaseUrl="https://company.livecareer.com";

	private Common(String browser)
		{
		if(browser.equals("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\mohit.sharma2\\workspace\\TestNGPractice\\geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\mohit.sharma2\\workspace\\TestNGPractice\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		}

	static Common getInstance() {
			if(instance==null)
			{
				instance = new Common(Common.browser);
			}
			return instance;
	}
	
	public void browsermax()
	{
		try{
			LogUtil.info("Maximise the Browser");
		driver.manage().window().maximize();
		}
		catch(Exception e)
		{
			LogUtil.error("Exception comes in browsermax" + e.getMessage());
		}
	}
	
	public void openBrowser() {
			Common.getInstance();
		}
	public void setBaseUrl(String baseUrl)
	{
		try{
		driver.get(baseUrl);
		Common.getInstance().browsermax();
		}
		catch(Exception e)
		{
			LogUtil.error("Exceptin Occured in SeBaseURL Methode" + e.getMessage());
		}
	}
	public void browserWait()
	{
		try{
			LogUtil.info("Implicitly Stop the Execution for 30 sec");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);
		}
		catch(Exception e)
		{
			LogUtil.error("Exception Occured in BrowserWait Methode" + e.getMessage());
		}
	}
	public void closeBrowser()
	{
		driver.close();;
	}
	
	public boolean verfifyCookieOnPageByName(String CookieName)
	{
		Cookie cookieValue=driver.manage().getCookieNamed(CookieName);
		if(cookieValue==null)
			return false;
		else
			return true;
	}
	
	public void deleteCookieonPageByName(String CookieName)
	{
		try{
			if(getInstance().verfifyCookieOnPageByName(CookieName))
				driver.manage().deleteCookieNamed(CookieName);
			else
				org.testng.Assert.fail("Cookie "+ CookieName +"Does not exist on Page");
		}
		catch(Exception e)
		{
			
		}
	}
	
}