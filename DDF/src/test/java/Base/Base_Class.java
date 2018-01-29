package Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import Model.Keywords;
import Reporting.HTMLReportGenerator;

public class Base_Class {
	public static Properties config=new Properties();
	public static Properties project=new Properties();
	public static Logger log=null;

	@BeforeClass
	public void setUp() throws IOException
	{
		PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\log4j.properties");
		log=Logger.getLogger("devpinoyLogger");
		log.debug("Loading Logs4j.property file for loggin");
		if(Constants.getConfigFilePath().exists() && Constants.getProductFilePath().exists())
		{
		FileInputStream configfile=new FileInputStream(Constants.getConfigFilePath());
		FileInputStream projectfile=new FileInputStream(Constants.getProductFilePath());
		
				
		config.load(configfile);
		log.debug("Config.property file loaded successfully");
		project.load(projectfile);
		log.debug("Project.property file loaded successfully");
		Keywords.getInstance().openBrowser(config.getProperty("Browser"));
		}
		else
		{
			log.debug("Config.properties and Project.properties File does not exist in provided path");
			System.out.println("Config.properties and Project.properties File does not exist in provided path");
		}
	}
	
	@AfterSuite
	public void tearDown()
	{
		HTMLReportGenerator.CreateHTMLReportContent();
		log.debug("Closing Current Instance of Browser");
		Keywords.driver.close();
		
	}
	
	
	
}
