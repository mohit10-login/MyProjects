package Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import Reporting.HTMLReportGenerator;

public class Base extends Keywords{
	
	public static Properties OR=new Properties();
	public static Properties Config=new Properties();
	public static FileInputStream fis=null;
	//Log4J Property File Mapped in Pom.xml file
	public static Logger log=null;
	
	@BeforeSuite
	public void setUp() throws Exception
	{
		//Below Property Configurator is User when Your Log4j.properties is in some other location thatn src\resources along with some java classs. 
		PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\test\\java\\Properties\\log4j.properties");
		log=Logger.getLogger("devpinoyLogger");
			try {
				fis= new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\Properties\\Config.properties");
			} catch (FileNotFoundException e) {
				log.info("Config.Properties not Found on Provided Location" + "Exception :- " + e.getStackTrace());
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
		//Keywords.getInstance().openBrowser(Browser);
		//Setting Up DB Connection
		//DBManager.getinstance().setConnectionToSQLServer();
	}
	
	
	
	@AfterSuite
	public void tearDown() throws SQLException
	{
		HTMLReportGenerator.CreateHTMLReportContent();
		log.debug("Closing all Browser Instances");
		Keywords.driver.close();
		Keywords.driver.quit();
		log.debug("Closing DB Connection");
		//DBManager.getinstance().CloseDBConnection();
	}
}
