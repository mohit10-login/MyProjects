package RoughWork;

import Utils.DBManager;

public class Rough {

	public static void main(String[] args) throws Exception {
		//SendMail obj=new SendMail();
		//obj.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, TestConfig.messageBody, TestConfig.attachmentPath, TestConfig.attachmentName);
		
		DBManager.getinstance().setConnectionToSQLServer();
		System.out.println(DBManager.getinstance().executesqlquery("select * from company where accessname='walmart'"));
	}
}
