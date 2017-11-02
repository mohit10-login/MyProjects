package RoughWork;

import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import Utils.DBManager;
import Utils.TestConfig;

public class Rough {

	public static void main(String[] args) throws AddressException, MessagingException, SQLException, ClassNotFoundException {
		//SendMail obj=new SendMail();
		//obj.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, TestConfig.messageBody, TestConfig.attachmentPath, TestConfig.attachmentName);
		
		//DBManager.setDbConnection();
		//DBManager.getQuery("select name from company where accessname='walmart'");
		//DBManager.getMysqlQuery("select * from company where accessname-'walmart'");
		DBManager.setDbConnection();
		DBManager.con=null;
	}
}
