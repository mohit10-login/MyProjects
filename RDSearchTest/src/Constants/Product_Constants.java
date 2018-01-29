package Constants;

public class Product_Constants {
	
	//public static int i=1;
	public static final String Product="RD";
	public static String Envirnment=null;
	
	public static final String RD_Production_URL ="https://resumes.livecareer.com/";
	public static final String RD_QA_URL ="https://qa-resumes.livecareer.com/";
	public static final String RD_REG_URL ="https://reg-resumes.livecareer.com/";
	public static final String RD_STG_URL ="https://stg-resumes.livecareer.com/";
	//================================================================================
	
	public static final String ExcelFilePath= System.getProperty("user.dir") + "\\Test-Data\\";
	public static final String ExcelFileName="RD-Search-Data.xlsx";
	public static final String ExcelSheetName="RD-Search";
	//================================================================================
	
	public static final String xpathjobTitle="//*[@id='txtJobTitle']";
	public static final String xpathLocation="//*[@id='txtLocation']";
	public static final String xpathFindResume="//*[@id='btnFind']";
	public static final String xpathJobAlertTest="//*[@class='jbAlrtWidgt']/p";
	public static final String xpathResultPageHeading="//*[@class='h4 disp-table-cell']/strong";
	public static final String xpathfirstresumeonresultpage="(//*[@class='resume-thumbnail'])[";
	public static final String xpathresume="(//*[@class='h3 resume-title'])[";
	
	//=====================================================================================
	
	public static final String fromemailaddress="100dqalivecareer@gmail.com";
	public static final String fromemailpassword="100dqa@lc";
	
	public static final String Toemailaddress="mohit.sharma2@bold.com";
	
	
}
