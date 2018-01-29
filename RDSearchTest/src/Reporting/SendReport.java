package Reporting;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;

import Constants.Product_Constants;
import Util.LogUtil;

public class SendReport {
	public static SendReport instance=null;
	private void SendReport()
	{
		
	}
	public static SendReport getinstance()
	{
		if(instance==null)
		{
			instance=new SendReport();
		}
		return instance;
	}

	@SuppressWarnings("deprecation")
	public void sendfinalrepot()
	{
		//=========================Setting Attachment File 
		EmailAttachment emailattachment=new EmailAttachment();
		emailattachment.setPath(Product_Constants.ExcelFilePath);
		
		emailattachment.setDisposition(EmailAttachment.ATTACHMENT);
		
		emailattachment.setDescription("Post Verification Report - <<RD-Search>>");
		emailattachment.setName(Product_Constants.ExcelSheetName);
		
		//=============================Setting Email Related Stuffs
		MultiPartEmail email=new MultiPartEmail();
		
		
		email.setHostName("smtp.gmail.com");
		email.setAuthentication(Product_Constants.fromemailaddress, Product_Constants.fromemailpassword);
		//email.setSmtpPort(29);
		
		email.setSSL(true);
		//=============================Setting From Email Address 
		try{
		email.setFrom(Product_Constants.fromemailaddress);
		}
		catch(Exception e)
		{
			LogUtil.error("<<=======================================Error Occured while seting From Email Address., Exception = >>"+ e.getMessage());
		}
		//=============================Setting From Email Subject
		email.setSubject(Product_Constants.Product + " - Automation Search Report on - " + Product_Constants.Envirnment);
		
		//=============================Setting From Email body
		try{
		email.setMsg("RD Search Execution Report" + "\n" + " Executed Envirnment = " + Product_Constants.Envirnment +"\n" + " Final Status = ");
		}
		catch(Exception e)
		{
			LogUtil.error("<<=======================================Error Occured while seting Email Message ., Exception = >>"+ e.getMessage());
		}
		
		//=============================Setting To Email Address
		try{
			email.addTo(Product_Constants.Toemailaddress);
		}
		catch(Exception e)
		{
			LogUtil.error("<<=======================================Error Occured while seting Sender in Mail ., Exception = >>"+ e.getMessage());
		}
		//=============================Setting Attachment
		try{
			email.attach(emailattachment);
		}
		catch(Exception e)
		{
			LogUtil.error("<<=======================================Error Occured while Attaching File in Mail ., Exception = >>"+ e.getMessage());
		}
		//=============================Send File
		try{
			email.send();
		}
		catch(Exception e)
		{
			LogUtil.error("<<=======================================Error Occured while Sending Mail ., Exception = >>"+ e.getMessage());
		}
		
	}
}
