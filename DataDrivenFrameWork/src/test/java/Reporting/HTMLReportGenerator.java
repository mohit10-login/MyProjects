package Reporting;

import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import Base.Base;

public class HTMLReportGenerator {
	public static StringBuilder FinalReport=new StringBuilder("<br><p align=\"center\"><font color='#000000' size=5.0/><b>Detailed Execution Summary<b></p><br>");
	public static StringBuilder Header=new StringBuilder("<table width='100%' cellspacing='0' BORDERCOLOR='#3E3535'><tr bgcolor='#6C6C96' align='center'><td border='1' ><font color='#FFFFFF' size=3.8/><b>Execution Report</b></td></tr><tr bgcolor='#6C6C96'><td border='1' align='center'><font color='#FFFFFF'/>S#</td><td border='1' align='center' width=auto><font color='#FFFFFF' />Test Case Description</td><td border='1' align='center' width=auto><font color='#FFFFFF'/>Result</td></tr>");
	public static StringBuilder Footer=new StringBuilder("</table><font color='Red' size=4.0><br><b>This is an automated e-mail.</b></font>");
	private static String HTMLReportPath=System.getProperty("user.dir") + "\\src\\test\\resources\\ExecutionOutputReport\\" + File.separator + "Output.html";
	public static ArrayList<String> TestCasesDiscription=new ArrayList<String>();
	
	
	public static void CreateHTMLReportContent()
	{
		Base.log.debug("Creating Final Execution HTML Report.");
		
		StringBuilder body=new StringBuilder("<tr bgcolor='#6C6C96'><td border='1' align='center'><font color='#000000'/>"+"1"+"</td><td border='1' align='center' width=auto><font color='#000000' />" +TestCasesDiscription.get(0) +"</td><td border='1' align='center' width=auto><font color='#000000'/>" + "Pass" +"</td></tr>");
		
		FinalReport.append(Header).append(body).append(Footer);
		HTMLReportGenerator.CreateFinalHTMLReport(HTMLReportPath, FinalReport.toString());
	}
	
	public static void CreateFinalHTMLReport(String filePath, String Content)
	{
		try{
		File file=new File(filePath);
		if(file.exists())
			file.delete();
		file.createNewFile();
		FileWriter fw=new FileWriter(file.getAbsoluteFile());
		BufferedWriter writer=new BufferedWriter(fw);
		writer.write(Content);
		writer.close();
		Base.log.debug("Execution HTML Report Genrated.");
		}
		catch(Exception e)
		{
			Base.log.debug("Error Occured While Creating Final HTML Execution Report, Exception = >" + e.getStackTrace());
		}
	}
}
