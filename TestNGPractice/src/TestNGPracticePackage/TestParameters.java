package TestNGPracticePackage;
import Utils.LogUtil;
import Utils.sqlutil;

import org.testng.annotations.Test;
import com.beust.jcommander.Parameter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;

import javax.naming.spi.DirStateFactory.Result;

import org.testng.ITestResult;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;



public class TestParameters {
int var=0;

//@Test
//public void getCompanyName() throws Exception
//{
//	sqlutil.getinstance().getSQLConnection();
//	sqlutil.getinstance().executesqlquery("select Name from company where accessname='test-lc-test'");
//	if(sqlutil.rs != null)
//	{
//		System.out.println(sqlutil.rs.getString("Name"));
//	}
//}

@Test (priority=1)
public void srpSearch()
{	
	LogUtil.starttestcase("srpSearch");
	Common.getInstance().openBrowser();
	Common.getInstance().setBaseUrl(Common.productbaseUrl);
	Common.getInstance().driver.findElement(By.id("txtSearchKeyword")).sendKeys("Management");
	Common.getInstance().driver.findElement(By.id("btnFind")).click();
	Common.getInstance().browserWait();
	Common.getInstance().driver.getCurrentUrl().equals(Common.productbaseUrl+"/search?q=management");
	LogUtil.endtestcase("srpSearch");
}
//@Test
//public void login()
//{
//	Common.getInstance().driver.findElement(By.xpath("(//i[@class='fa fa-user'])[2]")).click();
//	Common.getInstance().browserWait();
//	Common.getInstance().driver.findElement(By.xpath("//*[@id='txtUserName']")).sendKeys("mohit.sharma2@bold.com");
//	Common.getInstance().driver.findElement(By.id("txtPassword")).sendKeys("portal@1234");
//	Common.getInstance().driver.findElement(By.id("btnLogin")).click();
//}

@Test (priority=2)
@Parameters({"CookieName"})
public void VerifyVSTRCookieOnSRPPage(String CookieName)
{
	LogUtil.starttestcase("VerifyVSTRCookieOnSRPPage");
	if(!Common.getInstance().verfifyCookieOnPageByName(CookieName))
		org.testng.Assert.fail(CookieName + " Does Not Exist on Page, TestCase :- VerifyVSTRCookieOnSRPPage");
	LogUtil.endtestcase("VerifyVSTRCookieOnSRPPage");
}

@Test(priority=3)
@Parameters({"DeleteCookieName"})
public void deleteCookieonSRPPage(String CookieName)
{
	LogUtil.starttestcase("deleteCookieonSRPPage");
	Common.getInstance().deleteCookieonPageByName(CookieName);
	LogUtil.endtestcase("deleteCookieonSRPPage");
}

  @BeforeMethod
  public void beforeMethod(ITestResult result) throws Exception{
	  //LogUtil.starttestcase(result.getMethod().getMethodName().toString());
  }

  @AfterMethod
  public void afterMethod() {

  }
  
@BeforeSuite
@Parameters({"Testbrowser"})
public void beforeSuite(String testBrowser)
{
	Common.browser=testBrowser;
}
  
@AfterSuite
public void afterClass() {
  Common.getInstance().closeBrowser();
}



}