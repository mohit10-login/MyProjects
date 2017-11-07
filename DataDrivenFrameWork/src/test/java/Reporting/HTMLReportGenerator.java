package Reporting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import Base.Base;

public class HTMLReportGenerator {
	public static StringBuilder FinalReport=new StringBuilder("<br><p align=\"center\"><font color='#000000' size=5.0/><b>Detailed Execution Summary<b></p><br>");
	public static StringBuilder Header=new StringBuilder("<table border='1' width='100%' cellspacing='0' BORDERCOLOR='#3E3535'><tr bgcolor='#6C6C96' align='center'><td colspan='5'><font color='#FFFFFF' size=3.8/><b>Execution Report</b></td></tr><tr bgcolor='#6C6C96'><td align='center'><font color='#FFFFFF'/>S#</td><td align='center' width=auto><font color='#FFFFFF' />Test Case Description</td><td align='center' width=auto><font color='#FFFFFF'/>Result</td></tr>");
	public static StringBuilder Footer=new StringBuilder("</table><font color='Red' size=4.0><br><b>This is an automated e-mail.</b></font>");
	public static String HTMLReportPath=System.getProperty("user.dir") + "\\HTMLOutput\\" + File.separator + "Output.html";
	
	
	public static void CreateHTMLReportContent()
	{
		Base.log.debug("Creating Final Execution HTML Report.");
		StringBuilder body=new StringBuilder();
		
		FinalReport.append(Header).append(Footer);
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
