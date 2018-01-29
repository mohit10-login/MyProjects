package Reporting;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


import Constants.Product_Constants;
import Util.LogUtil;

public class SendJavaxmailReport {
	public static SendJavaxmailReport instance=null;
	private SendJavaxmailReport()
	{}
	
	public static SendJavaxmailReport getinstance()
	{
		if(instance==null)
			instance=new SendJavaxmailReport();
		return instance;
	}
	
	
	public void sendreport()
	{
		Properties prop=System.getProperties();
		prop.setProperty("mail.smtps.host", "mailpost.livecareer.com");
		prop.put("mail.smtp.host", "mailpost.livecareer.com" );
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.socketFactory.port", 29);
        prop.put("mail.smtp.port", 29);
		
		
		Session session=Session.getDefaultInstance(prop, new javax.mail.Authenticator(){protected PasswordAuthentication getPasswordAuthentication(){return new PasswordAuthentication("automationtesterbold@bold.com","automationtesterbold12");}});
		
		try{
			MimeMessage message=new MimeMessage(session);
			message.setFrom(new InternetAddress("automationtesterbold@bold.com"));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress("mohit.sharma2@bold.com"));
			message.addHeader("Content-type", "text/HTML; charset=UTF-8");
            message.addHeader("format", "flowed");
            message.addHeader("Content-Transfer-Encoding", "8bit");
			message.setSubject(Product_Constants.Product + " - Automation Search Report on - " + Product_Constants.Envirnment);
			message.setSentDate(new Date());
			
			
			BodyPart messagebodypart=new MimeBodyPart();
			messagebodypart.setText("RD Search Execution Report" + "\n" + " Executed Envirnment = " + Product_Constants.Envirnment +"\n" + " Final Status = ");
			
			BodyPart messagebodypart1=new MimeBodyPart();
			DataSource source=new FileDataSource(Product_Constants.ExcelFilePath+Product_Constants.ExcelFileName);
			
			messagebodypart1.setDataHandler(new DataHandler(source));
			messagebodypart1.setFileName(Product_Constants.ExcelFileName);
			
			Multipart multipart=new MimeMultipart();
			multipart.addBodyPart(messagebodypart);
			multipart.addBodyPart(messagebodypart1);
			
			message.setContent(multipart);
			
			//Transport transport=session.getTransport("smtps");
			//transport.connect("mailpost.livecareer.com", "automationtesterbold@bold.com", "automationtesterbold12");
			
			Transport.send(message);
			
		}
		catch(Exception e)
		{
			LogUtil.info("Exception Occured while Creating Mail, Excpetion = " + e.getMessage());
		}
	}
}
