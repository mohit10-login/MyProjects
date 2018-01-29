package BaseLibrary;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Util.LogUtil;

public class WebDriverFuncations {
	public static WebDriverFuncations instance=null;
	private WebDriver driver=null;
	
//==========================================================================================================================================================
	private void WebDriverFuncations()
	{

	}
//==========================================================================================================================================================	
	public static  WebDriverFuncations getinstanc()
	{
		if(instance==null)
		{
			instance=new WebDriverFuncations();
		}
		return instance;
	}
//==========================================================================================================================================================
	public void openBrowser()
	{
		LogUtil.info("<<=====================================(Opening Browser)==============================================>>");
		try
		{
		if(BaseVariables.browser.equals("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\bin\\Util\\Executables\\geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\bin\\Util\\Executables\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		}
		catch(Exception e)
		{
			LogUtil.error("Error while Execution <<openBrowser>> Methode, Excpetion = "+ e.getMessage());
		}
	}
//=========================================================================================================================================================
	public void setProductUrlInBrowser()
	{
		LogUtil.info("<<=====================================(Parsing Product URL into Browser)==============================================>>");
		try{
		driver.get(BaseVariables.baseurl);
		}
		catch(Exception e){
		LogUtil.error("Error while Execution <<setProductUrlInBrowser>> Methode, Excpetion = "+ e.getMessage());
		}
	}
//=========================================================================================================================================================
	public void browsermax()
	{
		try{
			driver.manage().window().maximize();
		}
		catch(Exception e)
		{
			LogUtil.error("Error Occured While Executing <<browsermax>> Methode, Exception = "+ e.getMessage());
		}
	}
//========================================================================================================================================================
	public void browserWait()
	{
		try{
			LogUtil.info("<<==============================================(Hold Excecution for 30 sec)====================================================>>");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);
		}
		catch(Exception e)
		{
			LogUtil.error("Error Occured While Executing <<browserWait>> Methode, Exception = " + e.getMessage());
		}
	}
//========================================================================================================================================================
	public void closeBrowser()
	{
		LogUtil.info("<<=====================================(Closing Browser)==============================================>>");
		driver.close();
	}
//========================================================================================================================================================
	public void EnterText(String element, String value)
	{
		try{
		driver.findElement(By.xpath(element)).sendKeys(value);
		}
		catch(Exception e)
		{
			LogUtil.error("<<==========================(Getting Error While Entering Text in " + element + " )================================================>>" + e.getMessage());
		}
	}
	//========================================================================================================================================================
	public void click(String element)
	{
		try{
		driver.findElement(By.xpath(element)).click();
		}
		catch(Exception e)
		{
			LogUtil.error("<<==========================(Getting Error While Clicking Over Element = " + element + " )================================================>>" + e.getMessage());
		}
	}
	//========================================================================================================================================================
	public String gettext(String element)
	{
		try{
		return driver.findElement(By.xpath(element)).getText();
		}
		catch(Exception e)
		{
			LogUtil.error("<<==========================(Getting Error While Fetching Text from Element = " + element + " )================================================>>" + e.getMessage());
			return null;
		}
	}
	//================================================================================================================================================
	public void browserback()
	{
		driver.navigate().back();
	}
}
