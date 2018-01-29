package Test;

import BaseLibrary.BaseVariables;
import BaseLibrary.WebDriverFuncations;
import Constants.Product_Constants;
import Model.ExcelReadWrite;
import Model.initiateResources;
import Util.LogUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.mustache.Model;

public class TestCase_Searches {
//Properties property=new Properties();
@Test
public void verifySearches()
{
	LogUtil.info("<<===================================================Starting Execution==================================================================>>");
	WebDriverFuncations.getinstanc().openBrowser();
	WebDriverFuncations.getinstanc().setProductUrlInBrowser();
	WebDriverFuncations.getinstanc().browsermax();
	ExcelReadWrite.getinstance().readFromExcel();
}
	
@BeforeSuite
public void BeforSuite() throws IOException
{

initiateResources.setConfigProperties();
if(BaseVariables.Product.equalsIgnoreCase("RD"))
{
	LogUtil.info("<<=========================(Executed Product " + BaseVariables.Product +"=======================================>>");
	LogUtil.info("<<=========================(Executed Envrinment " + BaseVariables.Envirnment +"=======================================>>");
if(BaseVariables.Envirnment.equalsIgnoreCase("Reg") || BaseVariables.Envirnment.equalsIgnoreCase("Regression"))
	BaseVariables.baseurl=Product_Constants.RD_REG_URL;
else if(BaseVariables.Envirnment.equalsIgnoreCase("QA"))
	BaseVariables.baseurl=Product_Constants.RD_QA_URL;
else if(BaseVariables.Envirnment.equalsIgnoreCase("STG") || BaseVariables.Envirnment.equalsIgnoreCase("Staging"))
	BaseVariables.baseurl=Product_Constants.RD_STG_URL;
else if(BaseVariables.Envirnment.equalsIgnoreCase("Prod") || BaseVariables.Envirnment.equalsIgnoreCase("Production"))
	BaseVariables.baseurl=Product_Constants.RD_Production_URL;
else{
	LogUtil.error("Invalid Product or Envirnment Define in Config file");
	org.testng.Assert.fail("Invalid Envirnment Defines in Config file");
}

}
else
{
	LogUtil.error("Invalid Product Define in Config file");
	org.testng.Assert.fail("Invalid Product Defines in Config file");
}
}	



@AfterSuite
public void AfterSuite()
{
	try{
	LogUtil.info("<<===================================Execution End >> Closing all Resources===================================================>>");
	WebDriverFuncations.getinstanc().closeBrowser();
	ExcelReadWrite.workbook.close();
	}
	catch(Exception e)
	{
		LogUtil.error("Error Occured while Executing <<After Suite Methode>>, Exception = " + e.getMessage());
	}
}

}
