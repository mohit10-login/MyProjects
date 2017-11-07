package TestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.Base;
import Base.Keywords;

public class Test_Cases extends Base{

	@Test
	public void TestCase_001()
	{
		Keywords.getInstance().navigate(Config.getProperty("SiteURL"));
		Keywords.getInstance().click(OR.getProperty("SignInLink"), "xpath");
		Keywords.getInstance().EnterText(OR.getProperty("UserName"), "xpath", "mohit.sharma2@bold.com");
		Keywords.getInstance().EnterText(OR.getProperty("Password"), "xpath", "test@1234");
		Keywords.getInstance().click(OR.getProperty("Login"), "xpath");
		Assert.fail();
	}
}
