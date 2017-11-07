package RoughWork;

import org.apache.log4j.Logger;

import Reporting.HTMLReportGenerator;
import Utils.SendMail;
import Utils.TestConfig;

public class Rough {
	public static Logger log=Logger.getLogger("devpinoyLogger");
	public static void main(String[] args) throws Exception {
		log.debug("Hi");
		HTMLReportGenerator.CreateHTMLReportContent();
		//SendMail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, HTMLReportGenerator.FinalReport.append(HTMLReportGenerator.Header).append(HTMLReportGenerator.Footer).toString(), TestConfig.attachmentPath, TestConfig.attachmentName);
		//System.out.println("End");
		//DBManager.getinstance().setConnectionToSQLServer();
		//System.out.println(DBManager.getinstance().executesqlquery("select name from company where accessname='walmart'"));
		
		//HTMLReportGenerator.CreateHTMLReportContent();
	}
}
