package Reporting;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Utils.TestUtil;

public class CustomListeners implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		ITestNGMethod testNgMethod= result.getMethod();
		Method method= testNgMethod.getConstructorOrMethod().getMethod();
		Test testAnnotation=(Test) method.getAnnotation(Test.class);
		if(testAnnotation != null)
			//System.out.println(testAnnotation.description());
			HTMLReportGenerator.TestCasesDiscription.add(testAnnotation.description());
		else
			HTMLReportGenerator.TestCasesDiscription.add("Test Case Description Not Defined in TestNG Test Methode");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TestUtil.CaptureScreenShot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("<a href="+TestUtil.SCFileName+" target=\"_blank\">Screenshot Captured</a>");
		Reporter.log("<br>");
		Reporter.log("<a href="+TestUtil.SCFileName+" target=\"_blank\"><img src="+TestUtil.SCFileName+" height=200 width=200></a>");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
