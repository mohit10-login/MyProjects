package Rough;

import java.io.IOException;


import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import Utils.DBManager;
import Utils.SendMail;
import Utils.SendMail.*;
import Utils.TestConfig;

public class Rough_Work {

	public static void main(String[] args) throws Exception
	{
		//Base_Class obj=new Base_Class();
		//obj.setUp();
		//System.out.println(Base_Class.config.getProperty("Browser"));
		//SendMail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, TestConfig.messageBody, TestConfig.attachmentPath, TestConfig.attachmentName);
		
		
		DBManager.getinstance().setConnectionToSQLServer();
		DBManager.getinstance().executesqlquery("select companyid from company where accessname='walmart'");
		
		

	}

}
