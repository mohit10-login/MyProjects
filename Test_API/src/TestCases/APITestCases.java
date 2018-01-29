package TestCases;

import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.eclipse.jetty.websocket.api.StatusCode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;

import API.HTTPMethods;
import API.InitiateResources;
import BaseLibrary.BaseVariables;

public class APITestCases {
	ArrayList<Object> list=new ArrayList<Object>();
	JSONObject APIResponse;
	JSONArray JsonArray;
	int Statuscode;
	String addedCompanyID;
	
	
	@Test (description="Verify V2-GETCOMPANY Methode with Accessname and IsTestCompany=False")
	public void TestCase_001() throws ClientProtocolException, IOException, JSONException
	{
		String AccessName="Fedex";
		String HitMethodeURL=BaseVariables.getInstance().getBaseURL()+"companies/v2/companies/CompanyAccessName?param_value=" +AccessName+"&includeTestCompany=false";
		
		list=HTTPMethods.getInstance().Get(HitMethodeURL, true);
		Statuscode=(int)list.get(0);
		if(Statuscode==200)
		{
		APIResponse=(JSONObject) list.get(1);
		}
		else
		{
			org.testng.Assert.fail("This Test Case Fail Because Status Code " + Statuscode + " is not matched with Expected Status Code = 200");
		}
		if(Statuscode==200 && !APIResponse.get("AccessName").toString().equalsIgnoreCase(AccessName))
		{
			org.testng.Assert.fail("This Test Case Fail Because Access Name " + APIResponse.get("AccessName") + " is not matched with Expected AccessName " + AccessName);
		}
		//System.out.println(Statuscode+ " " + APIResponse.getString("AccessName"));	
	}
	
	@Test (priority=1, description="Verify V2-POSTCompany Methode with Valid Data")
	public void TestCase_002() throws ClientProtocolException, IOException, JSONException
	{
		String JsontoPost="{\"Name\": \"Automation API Company\",\"WebsiteUrl\": \"https://www.bold.com\",  \"City\": \"New York\",  \"State\": \"NY\",  \"Address\": \"Testing Company\",  \"ZipCode\": \"123456978\",  \"Country\": \"NY\",  \"Latitude\": 5,  \"Longitude\": 5,  \"CreatedBy\": \"API Automation\",  \"ModifiedBy\": \"API Automation\",  \"IsTestCompany\": false,  \"Source\": \"Testing\"}";
		String HitMethodeURL=BaseVariables.getInstance().getBaseURL()+"companies/v2/company";
		
		list=HTTPMethods.getInstance().Post(HitMethodeURL, JsontoPost, true);
		Statuscode=(int)list.get(0);
		if((int)list.get(0)==200)
		{
			APIResponse=(JSONObject) list.get(1);
		}
		else
			org.testng.Assert.fail("This Test Case Fail Because Status Code " + Statuscode + " is not matched with Expected Status Code = 200");
		if(!APIResponse.get("Name").equals("Automation API Company"))
		{
			org.testng.Assert.fail("This Test Case Fail Because Inserted Company Name is not matched with Expected Company Name");
		}
		this.addedCompanyID=(String)APIResponse.get("CompanyId");
		System.out.println(addedCompanyID);
	}
	
	@Test(priority=3, description="Verify V2-DELETECompany Methode with Valid Data")
	public void TestCase_003() throws ClientProtocolException, IOException, JSONException, InterruptedException
	{
		String HitMethodeURL=BaseVariables.getInstance().getBaseURL()+"companies/v2/company?companyId="+addedCompanyID+"&userId="+addedCompanyID+"&RoleId=600";
		
		list=HTTPMethods.getInstance().Delete(HitMethodeURL, true);
		Statuscode=(int)list.get(0);
		if(!(Statuscode==200))
		{
			org.testng.Assert.fail("This Test Case Fail Because Response Status Code " + Statuscode + " is not matched with Excpected Status Code = 200");
		}
	}
	
	@Test(priority=2, description="Verify V2-Put_addcompanyprocesscompleteded Methode with Valid Data")
	public void TestCase_004() throws ClientProtocolException, IOException, JSONException
	{
		String HitMetodeURL=BaseVariables.getInstance().getBaseURL()+"companies/v2/addcompanyprocesscompleted?companyID="+addedCompanyID;
		
		String JsontoPost="{\"userId\": \"3819ba06-3aed-4307-be1e-983c3710b99f\"}";
		
		list=HTTPMethods.getInstance().Put(HitMetodeURL, JsontoPost, true);
		Statuscode=(int)list.get(0);
		if(!(Statuscode==200))
			org.testng.Assert.fail("This Test Case Fail Because Response Status Code " + Statuscode + " is not matched with Excpected Status Code = 200");
	}
	
	
	@BeforeSuite
	public void BeforeSuite() throws FileNotFoundException, IOException, JSONException
	{
		InitiateResources.getInstance().initiateBaseVariables();
	}
	
	@AfterSuite
	public void AfterSuite() throws IOException
	{
	}
}
