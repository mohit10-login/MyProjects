package RoughWork;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import Base.Keywords;
import Reporting.HTMLReportGenerator;
import Utils.DBManager;

public class Rough {
	public static void main(String[] args) throws Exception 
	{
//		//Creating Object of Class where i kept out basic methodes to perfor action on Web Page.
//		Keywords obj=new Keywords();
//		obj.openBrowser("Chrome");
//		obj.navigate("https://www.cheapoair.com");
//		Thread.sleep(1000);
//		//Clicking on Pop Up model that appears on Initial Screen
//		obj.click("//a[@class='signupClose icon ic-cancel-fill']", "xpath");
//		Thread.sleep(1000);
//		//Clicking on To Date Field to Open Calander
//		obj.click("//input[@id=\"departCalendar_0\"]", "xpath");
//		//Print Current Date Dyanamic ID that will after we user to modification in Dates
//		System.out.println(Keywords.driver.findElement(By.xpath("//li[@data-placement='bottom' and contains(@class,'today')]")).getAttribute("data-ember-action").toString());
//		//Store Current Date Dyanamic ID in Date variable
//		int date=Integer.parseInt(Keywords.driver.findElement(By.xpath("//li[@data-placement='bottom' and contains(@class,'today')]")).getAttribute("data-ember-action"));
//		int Todate= date+20;
//		//Click on Dates on Calander by Passing out above ToDate [To date could be any as per your requirement.]
//		obj.click("//li[@data-placement='bottom' and @data-ember-action='"+Todate+"']", "xpath");
//		int FromDate= Todate+5;
//		Thread.sleep(1000);
//		//Similarly click for From Date.
//		obj.click("//li[@data-placement='bottom' and @data-ember-action='"+FromDate+"']", "xpath");
		
		DBManager.getinstance().setConnectionToSQLServer();
		DBManager.getinstance().executesqlquery("select companyid from company where accessname='walmar'");
		
	}
}
