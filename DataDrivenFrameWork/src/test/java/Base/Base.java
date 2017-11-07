package Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.internal.Utils;

import Utils.DBManager;

public class Base extends Keywords{
	
	public static Properties OR=new Properties();
	public static Properties Config=new Properties();
	public static FileInputStream fis=null;
	//Log4J Property File Mapped in Pom.xml file
	public static Logger log=Logger.getLogger("devpinoyLogger");
	
	@BeforeSuite
	public void setUp() throws Exception
	{
			try {
				fis= new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\Properties\\Config.properties");
			} catch (FileNotFoundException e) {
				log.debug("Config.Properties not Found on Provided Location" + "Exception :- " + e.getStackTrace());
			}
			
			try {
				log.debug("Loading Config.properties File");
				Config.load(fis);
			} catch (IOException e) {
				log.debug("Error Occured while Loading Config.Properties File" + "Exception :- " + e.getStackTrace());
			}
			
			try {
				fis= new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\Properties\\OR.properties");
			} catch (FileNotFoundException e) {
				log.debug("OR.Properties not Found on Provided Location");
			}
			
			try {
				log.debug("Loading OR.properties File");
				OR.load(fis);
			} catch (IOException e) {
				log.debug("Error Occured while Loading Config.Properties File" + "Exception :- " + e.getStackTrace());
			}
		//Open Test Browser for your Test
		Keywords.getInstance().openBrowser(Config.getProperty("Browser").trim().toString());
		//Setting Up DB Connection
		DBManager.getinstance().setConnectionToSQLServer();
	}
	
	
	
	@AfterSuite
	public void tearDown() throws SQLException
	{
		log.debug("Closing all Browser Instances");
		Keywords.driver.close();
		Keywords.driver.quit();
		log.debug("Closing DB Connection");
		DBManager.getinstance().CloseDBConnection();
	}
}
