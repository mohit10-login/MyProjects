package Test_Cases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Model.Keywords;

public class TestCase extends Keywords{

	@Test(description="Verify Login with Valid Credentials")
	public void TestCaseID_001()
	{
		Keywords.getInstance().navigate(config.getProperty("SiteURL"));
		Keywords.getInstance().click(project.getProperty("SignInLink"), "xpath");
		Keywords.getInstance().EnterText(project.getProperty("UserName"), "xpath", "mohit.sharma2@bold.com");
		Keywords.getInstance().EnterText(project.getProperty("Password"), "xpath", "test@1234");
		Keywords.getInstance().click(project.getProperty("Login"), "xpath");
		Assert.fail();
	}
	
}
