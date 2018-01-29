package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;

import java.io.IOException;
import java.net.*;
import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLHandshakeException;
import javax.swing.text.AbstractDocument.BranchElement;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class Keywords{
	private static Keywords instance=null;
	
	public Keywords()
	{
		
	}
	
	public static Keywords getInstance()
	{
		if(instance==null)
			instance=new Keywords();
		return instance;
	}
	
	
	
	public static WebDriver driver;
	Actions action;
	
	//This method open Browse Based on Input
	public void openBrowser(String browser)
	{	
		//Base.log.debug("Opening Brower => " + browser);
		if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\java\\Executables\\geckodriver.exe");
			driver =new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			this.action=new Actions(driver);
		}
		else if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\java\\Executables\\Chromedriver.exe");
			driver =new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			this.action=new Actions(driver);
		}
		else if(browser.equalsIgnoreCase("id"))
		{
			driver =new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			this.action=new Actions(driver);
		}
		else if(browser.equalsIgnoreCase("opera"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\mohit.sharma2\\workspace\\Drivers\\operadriver.exe");
			driver =new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			this.action=new Actions(driver);
		}
		else
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\java\\Executables\\Chromedriver.exe");
			Base.log.debug("Invalid Browse Name Hence open Defaul Browser as => Chrome Browser");
			driver =new ChromeDriver();
			this.action=new Actions(driver);
		}
	}
	
	//This Method redirects user to Provided URL and then Maximize the Browser 
	public void navigate(String url)
	{
		if(!(url.startsWith("https://")) && !(url.startsWith("http://")))
			url="https://" + url;
		//Base.log.debug("Navigate to URL => " + url);
		driver.get(url);
		driver.manage().window().maximize();
		
	}
	
	// This Method Enter Text in Element or Text Box Based of locator Passed as an Parameter
	public boolean EnterText(String locator, String locatortype, String input)
	{
		boolean response=false;
		try{
		switch (locatortype) {
		case "xpath":
			driver.findElement(By.xpath(locator)).sendKeys(input);
			response=true;
			break;
		case "linktext":
			driver.findElement(By.linkText(locator)).sendKeys(input);
			response=true;
			break;
		case "classname":
			driver.findElement(By.className(locator)).sendKeys(input);
			response=true;
			break;
		case "id":
			driver.findElement(By.id(locator)).sendKeys(input);
			response=true;
			break;
		case "cssselector":
			driver.findElement(By.cssSelector(locator)).sendKeys(input);
			response=true;
			break;
		case "name":
			driver.findElement(By.name(locator)).sendKeys(input);
			response=true;
			break;
		case "partiallinktext":
			driver.findElement(By.partialLinkText(locator)).sendKeys(input);
			response=true;
			break;
		case "tagname":
			driver.findElement(By.tagName(locator)).sendKeys(input);
			response=true;
			break;
		default:
			Base.log.debug("Invalid Locator Type Entered in Sheet, Kindly Check and corrent the same, It should be from ('xpath','linktext','classname','id','cssselector','name','partiallinktext','tagname',)");
			break;
		}
		Base.log.debug("Entering Data/Input => " + input + " into => " + locatortype +" =>" + locator);
	}
		catch(Exception e)
		{
			Base.log.debug("Unable to Locate => "+ locatortype + " => " + locator + " on page");
		}
		return response;
	}
	
	//This Method First Clear the Text Box or element and then Enter Text in Element or Text Box Based of locator Passed as an Parameter
	public boolean ClearAndEnterText(String locator, String locatortype, String input)
	{
		boolean response=false;
		try{
		switch (locatortype) {
		case "xpath":
			driver.findElement(By.xpath(locator)).clear();
			driver.findElement(By.xpath(locator)).sendKeys(input);
			response=true;
			break;
		case "linktext":
			driver.findElement(By.linkText(locator)).clear();
			driver.findElement(By.linkText(locator)).sendKeys(input);
			response=true;
			break;
		case "classname":
			driver.findElement(By.className(locator)).clear();
			driver.findElement(By.className(locator)).sendKeys(input);
			response=true;
			break;
		case "id":
			driver.findElement(By.id(locator)).clear();
			driver.findElement(By.id(locator)).sendKeys(input);
			response=true;
			break;
		case "cssselector":
			driver.findElement(By.cssSelector(locator)).clear();
			driver.findElement(By.cssSelector(locator)).sendKeys(input);
			response=true;
			break;
		case "name":
			driver.findElement(By.name(locator)).clear();
			driver.findElement(By.name(locator)).sendKeys(input);
			response=true;
			break;
		case "partiallinktext":
			driver.findElement(By.partialLinkText(locator)).clear();
			driver.findElement(By.partialLinkText(locator)).sendKeys(input);
			response=true;
			break;
		case "tagname":
			driver.findElement(By.tagName(locator)).clear();
			driver.findElement(By.tagName(locator)).sendKeys(input);
			response=true;
			break;
		default:
			System.out.println("Invalid Locator Type Entered in Sheet, Kindly Check and corrent the same, It should be from ('xpath','linktext','classname','id','cssselector','name','partiallinktext','tagname',)");
			break;
		}
		}
		catch(Exception e)
		{
			System.out.println("Unable to Locate " + locatortype + " = " + locator + " on page");
		}
		return response;
	}
	
	//This Method check the provided Element is Displayed on Web Page and Return the Boolean.
	public boolean ElementDisplayed(String locator, String locatortype)
	{
		boolean response=false;
		try{
			switch (locatortype) {
			case "xpath":
				response=driver.findElement(By.xpath(locator)).isDisplayed();
				break;
			case "linktext":
				response=driver.findElement(By.linkText(locator)).isDisplayed();
				break;
			case "classname":
				response=driver.findElement(By.className(locator)).isDisplayed();
				break;
			case "id":
				response=driver.findElement(By.id(locator)).isDisplayed();
				break;
			case "cssselector":
				response=driver.findElement(By.cssSelector(locator)).isDisplayed();
				break;
			case "name":
				response=driver.findElement(By.name(locator)).isDisplayed();
				break;
			case "partiallinktext":
				response=driver.findElement(By.partialLinkText(locator)).isDisplayed();
				break;
			case "tagname":
				response=driver.findElement(By.tagName(locator)).isDisplayed();
				break;
			default:
				System.out.println("Invalid Locator Type Entered in Sheet, Kindly Check and corrent the same, It should be from ('xpath','linktext','classname','id','cssselector','name','partiallinktext','tagname',)");
				break;
			}
			}
			catch(Exception e)
			{
				System.out.println("Unable to Locate " + locatortype + " = " + locator + " on page");
			}
		return response;
	}
	
	//This Method check the provided Element is Not Displayed on Web Page and Return true if its not Displayed else return false if displayed on page.
	public boolean ElementNotDisplayed(String locator, String locatortype)
	{
		boolean response=false;
		try{
			switch (locatortype) {
			case "xpath":
				response=driver.findElement(By.xpath(locator)).isDisplayed();			
				break;
			case "linktext":
				response=driver.findElement(By.linkText(locator)).isDisplayed();
				break;
			case "classname":
				response=driver.findElement(By.className(locator)).isDisplayed();
				break;
			case "id":
				response=driver.findElement(By.id(locator)).isDisplayed();
				break;
			case "cssselector":
				response=driver.findElement(By.cssSelector(locator)).isDisplayed();
				break;
			case "name":
				response=driver.findElement(By.name(locator)).isDisplayed();
				break;
			case "partiallinktext":
				response=driver.findElement(By.partialLinkText(locator)).isDisplayed();
				break;
			case "tagname":
				response=driver.findElement(By.tagName(locator)).isDisplayed();
				break;
			default:
				System.out.println("Invalid Locator Type Entered in Sheet, Kindly Check and corrent the same, It should be from ('xpath','linktext','classname','id','cssselector','name','partiallinktext','tagname',)");
				break;
			}
			}
			catch(Exception e)
			{
				System.out.println("Unable to Locate " + locatortype + " = " + locator + " on page");
			}
		if(response==false)
		return true;
		else
			return false;
	}
	
	//This method is Back the Browser
	public boolean back()
	{
		driver.navigate().back();
		return true;
	}
	
	//This method is Forward the Browser
	public boolean forward()
	{
		driver.navigate().forward();
		return true;
	}
	
	//This method is Back the Browser
	public boolean Refresh()
	{
		driver.navigate().refresh();
		return true;
	}
	
	//This method is Pause the Execution for 30 secs
	public boolean Pause()
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);
		return true;
	}
	
	//This mehod is click over Webelement based on locator and locator type passed as an parameter and return boolean.
	public boolean click(String locator, String locatortype)
	{
		boolean response=false;
		try{
			switch (locatortype) {
			case "xpath":
				driver.findElement(By.xpath(locator)).click();
				response=true;
				break;
			case "linktext":
				driver.findElement(By.linkText(locator)).click();
				response=true;
				break;
			case "classname":
				driver.findElement(By.className(locator)).click();
				response=true;
				break;
			case "id":
				driver.findElement(By.id(locator)).click();
				response=true;
				break;
			case "cssselector":
				driver.findElement(By.cssSelector(locator)).click();
				response=true;
				break;
			case "name":
				driver.findElement(By.name(locator)).click();
				response=true;
				break;
			case "partiallinktext":
				driver.findElement(By.partialLinkText(locator)).click();
				response=true;
				break;
			case "tagname":
				driver.findElement(By.tagName(locator)).click();
				response=true;
				break;
			default:
				System.out.println("Invalid Locator Type Entered in Sheet, Kindly Check and corrent the same, It should be from ('xpath','linktext','classname','id','cssselector','name','partiallinktext','tagname',)");
				break;
			}
			}
			catch(Exception e)
			{
				System.out.println("Unable to Locate " + locatortype + " = " + locator + " on page");
			}
		return response;
	}
	
	//This method is perform double click on web element passed as an parameter.
	public boolean DoubleClick(String locator, String locatortype)
	{
		boolean response=false;
		try{
			switch (locatortype) {
			case "xpath":
				action.doubleClick(driver.findElement(By.xpath(locator))).perform();
				response=true;
				break;
			case "linktext":
				action.doubleClick(driver.findElement(By.linkText(locator))).perform();
				response=true;
				break;
			case "classname":
				action.doubleClick(driver.findElement(By.className(locator))).perform();
				response=true;
				break;
			case "id":
				action.doubleClick(driver.findElement(By.id(locator))).perform();
				response=true;
				break;
			case "cssselector":
				action.doubleClick(driver.findElement(By.cssSelector(locator))).perform();
				response=true;
				break;
			case "name":
				action.doubleClick(driver.findElement(By.name(locator))).perform();
				response=true;
				break;
			case "partiallinktext":
				action.doubleClick(driver.findElement(By.partialLinkText(locator))).perform();
				response=true;
				break;
			case "tagname":
				action.doubleClick(driver.findElement(By.tagName(locator))).perform();
				response=true;
				break;
			default:
				System.out.println("Invalid Locator Type Entered in Sheet, Kindly Check and corrent the same, It should be from ('xpath','linktext','classname','id','cssselector','name','partiallinktext','tagname',)");
				break;
			}
			}
			catch(Exception e)
			{
				System.out.println("Unable to Locate " + locatortype + " = " + locator + " on page");
			}
		return response;
	}
	
	public boolean VerifyElementText(String locatortype, String locator, String ExpectedText)
	{
		boolean response=false;
		String ActualText=null;
		try{
			switch (locatortype) {
			case "xpath":
				ActualText=driver.findElement(By.xpath(locator)).getText().toString().trim();
				if(ActualText.equals(ExpectedText))
					response=true;
				else
					response=false;
				break;
			case "linktext":
				ActualText=driver.findElement(By.linkText(locator)).getText().toString().trim();
				if(ActualText.equals(ExpectedText))
					response=true;
				else
					response=false;
				break;
			case "classname":
				ActualText=driver.findElement(By.className(locator)).getText().toString().trim();
				if(ActualText.equals(ExpectedText))
					response=true;
				else
					response=false;
				break;
			case "id":
				ActualText=driver.findElement(By.id(locator)).getText().toString().trim();
				if(ActualText.equals(ExpectedText))
					response=true;
				else
					response=false;
				break;
			case "cssselector":
				ActualText=driver.findElement(By.cssSelector(locator)).getText().toString().trim();
				if(ActualText.equals(ExpectedText))
					response=true;
				else
					response=false;
				break;
			case "name":
				ActualText=driver.findElement(By.name(locator)).getText().toString().trim();
				if(ActualText.equals(ExpectedText))
					response=true;
				else
					response=false;
				break;
			case "partiallinktext":
				ActualText=driver.findElement(By.partialLinkText(locator)).getText().toString().trim();
				if(ActualText.equals(ExpectedText))
					response=true;
				else
					response=false;
				break;
			case "tagname":
				ActualText=driver.findElement(By.tagName(locator)).getText().toString().trim();
				if(ActualText.equals(ExpectedText))
					response=true;
				else
					response=false;
				break;
			default:
				System.out.println("Invalid Locator Type Entered in Sheet, Kindly Check and corrent the same, It should be from ('xpath','linktext','classname','id','cssselector','name','partiallinktext','tagname',)");
				break;
			}
			}
			catch(Exception e)
			{
				System.out.println("Unable to Locate " + locatortype + " = " + locator + " on page");
			}
		return response;
	}
	//This method check the response HTTP code of Given URL and check with Expected Code provided as an Argument and returns true if matched else returns false. 
	public static boolean CheckResponseCode(String ThisURL, int ExpectedCode) throws UnknownHostException, IOException
	{
		if(ThisURL.contains("()") || ThisURL.contains("(") || ThisURL.contains(")") || ThisURL.equals(null))
				{
			//Checking for Invalid URL's
					return false;
				}
		if(!(ThisURL.startsWith("https://")) || !(ThisURL.startsWith("http://")))
		{
			ThisURL = "https://" + ThisURL;
		}
		int actualCode=0;
		HttpURLConnection con=null;
		URL url=new URL(ThisURL);
		try{
		con=(HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.connect();
		}
		catch(Exception e)
		{
			actualCode=404;
		}
		if(actualCode!=404)
		actualCode=con.getResponseCode();
		if(actualCode==ExpectedCode)
			return true;
		else
			return false;
	}
	//This Method perform ArrowKeyDown Function from Keyboard.
	public boolean ArrowKeyDown()
	{
		Actions action=new Actions(driver);
		try{
		action.sendKeys(Keys.ARROW_DOWN).build().perform();
		return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	//This Method perform ArrowKeyUp Function from Keyboard.
	public boolean ArrowKeyUp()
	{
		try{
		action.sendKeys(Keys.ARROW_UP).build().perform();
		return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	//This Method perform ArrowKeyUp Function from Keyboard.
	public boolean PressTabKey()
	{
		try{
		action.sendKeys(Keys.TAB).build().perform();
		return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	//This Method perform Enter Key Funcation from Keyboard.
	public boolean PressEnterKey()
	{
		try{
		action.sendKeys(Keys.ENTER).build().perform();
		return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public boolean CheckAllLinksonCurrentPage() throws UnknownHostException, IOException
	{
//		if(!(URL.startsWith("https://")) || !(URL.startsWith("http://")))
//			{
//				URL= "https://" + URL;
//			}
		List <WebElement> list= driver.findElements(By.tagName("a"));
		String link=null;
		
		for (WebElement Element : list) {
			link=Element.getAttribute("href");
			if(!(link.equals(null)))
			{
				//System.out.println(link);
			System.out.println(link + " ===>===>===>===>===>===>===>===> " + Keywords.CheckResponseCode(link, 200));
			}
			else
			{
				System.out.println("Found <a> tag having NULL on Page");
			}
		}
		
		return true;
	}
	
	
	

}
