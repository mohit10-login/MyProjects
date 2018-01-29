package WebDriverKeywords;

import java.io.IOException;

import Utils.sqlutil;

public class CallingKeywords {
public static void main(String args[]) throws Exception
{
	Keywords obj=new Keywords();
	//obj.openBrowser("chorme");
	//obj.navigate("qa-company.livecareer.com");
//	//System.out.println(obj.CheckResponseCode("http://www.buckleymtestediagroup.com/testing.html", 404));
//System.out.println(obj.CheckResponseCode("https://qa.livecareer.com/dashboard", 200));
//	obj.EnterText("txtSearchKeyword", "id", "tech");
//	obj.ArrowKeyDown();
//	Thread.sleep(2000);
//	obj.ArrowKeyDown();
//	Thread.sleep(2000);
//	obj.PressEnterKey();
//	Thread.sleep(2000);
	//obj.CheckAllLinksonCurrentPage();
	
	sqlutil.getinstance().getSQLConnection();
	sqlutil.getinstance().executesqlquery("select companyid from company where accessname='walmart'");
}
}
