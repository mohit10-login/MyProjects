package Model;

import org.openqa.selenium.WebDriver;

import BaseLibrary.WebDriverFuncations;
import Constants.Product_Constants;
import Util.LogUtil;

public class BusinessLogic{
	public String jobalertboxtext=null;
	public String ResultPageHeading=null; 
	public String resumetext=null;

	public boolean logic(String job, String loc)
	{
		try{
			if(!(job.equals("")) && !(loc.equals("")))
			{
				WebDriverFuncations.getinstanc().EnterText(Product_Constants.xpathjobTitle, job);
				
				//Below Line Commented becase Now RD have only one search box.
				//WebDriverFuncations.getinstanc().EnterText(Product_Constants.xpathLocation, loc);
				
				WebDriverFuncations.getinstanc().click(Product_Constants.xpathFindResume);
				//jobalertboxtext= WebDriverFuncations.getinstanc().gettext(Product_Constants.xpathJobAlertTest);
				ResultPageHeading=WebDriverFuncations.getinstanc().gettext(Product_Constants.xpathResultPageHeading);
				
				//Below Line Commented becase Now RD have only one search box.
				//if(jobalertboxtext.contains(job) && ResultPageHeading.contains(job) && ResultPageHeading.contains(loc))
				
				if(ResultPageHeading.contains(job))
				{
					//Below Line Commented becase Now RD have only one search box.
					//loc=loc.split(",")[0].toString();
					int i=1;
					while(i>0 && i<11)
					{
					WebDriverFuncations.getinstanc().click(Product_Constants.xpathfirstresumeonresultpage+i+"]");
					resumetext=WebDriverFuncations.getinstanc().gettext(Product_Constants.xpathresume);
					
					if(resumetext.contains(job) || resumetext.contains(loc))
					{
						return true;
					}
					else
					{
						i++;
						WebDriverFuncations.getinstanc().browserback();
					}
					}
				}
				else
					return false;
			}
			else if(job.equals("") && !(loc.equals("")))
			{	
				WebDriverFuncations.getinstanc().EnterText(Product_Constants.xpathLocation, loc);
				WebDriverFuncations.getinstanc().click(Product_Constants.xpathFindResume);
				jobalertboxtext= WebDriverFuncations.getinstanc().gettext(Product_Constants.xpathJobAlertTest);
				ResultPageHeading=WebDriverFuncations.getinstanc().gettext(Product_Constants.xpathResultPageHeading);
				if(jobalertboxtext.contains(loc) && ResultPageHeading.contains(loc))
				{
				loc=loc.split(",")[0].toString();
				int i=1;
				while(i>0 && i<6)
				{
					WebDriverFuncations.getinstanc().click(Product_Constants.xpathfirstresumeonresultpage+i+"]");
					resumetext=WebDriverFuncations.getinstanc().gettext(Product_Constants.xpathresume);
					
					if(resumetext.contains(loc))
					{
						return true;
					}
					else
					{
						i++;
						WebDriverFuncations.getinstanc().browserback();
					}
				}
				}
				else
					return false;
			}
			else if (loc.equals("") && !(job.equals("")))
			{	
				WebDriverFuncations.getinstanc().EnterText(Product_Constants.xpathjobTitle, job);
				WebDriverFuncations.getinstanc().click(Product_Constants.xpathFindResume);
				
				//Below Line Commented becase Now RD have only one search box.
				//jobalertboxtext= WebDriverFuncations.getinstanc().gettext(Product_Constants.xpathJobAlertTest);
				
				ResultPageHeading=WebDriverFuncations.getinstanc().gettext(Product_Constants.xpathResultPageHeading);
				
				//Below Line Commented becase Now RD have only one search box.
				//if(jobalertboxtext.contains(job) && ResultPageHeading.contains(job))
				if(ResultPageHeading.contains(job))
				{
				//loc=loc.split(",").toString();
				int i=1;
				while(i>0 && i<6)
				{
					//WebDriverFuncations.getinstanc().click(Product_Constants.xpathfirstresumeonresultpage+i+"]");
					resumetext=WebDriverFuncations.getinstanc().gettext((Product_Constants.xpathresume+i+"]"));
					if(resumetext.contains(job))
					{
						return true;
					}
					else
					{
						i++;
						//WebDriverFuncations.getinstanc().browserback();
					}
				}
				}
				else
					return false;
				
			}
			else 
			{
				return false;
			}
		}
		catch(Exception e)
		{
			LogUtil.error(e.getMessage());
		}
		return false;
		
	}
}
