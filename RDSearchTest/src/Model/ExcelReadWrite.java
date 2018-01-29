/**
 * 
 */
package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.bcel.verifier.exc.LocalVariableInfoInconsistentException;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BaseLibrary.BaseVariables;
import Constants.Product_Constants;
import Reporting.SendJavaxmailReport;
import Reporting.SendReport;
import Util.LogUtil;

public class ExcelReadWrite extends BusinessLogic{
	private static ExcelReadWrite instance=null;
	public static XSSFWorkbook workbook=null;
	public static File src=null;
	public static FileInputStream fis=null;
	public static XSSFSheet  sheet= null;
	private void ExcelReadWrite()
	{
		
	}
	public static ExcelReadWrite getinstance()
	{
		if(instance==null)
		{
			instance = new ExcelReadWrite();
		}
		return instance;
	}
	//====================================================================================================================
	public static void readFromExcel()
	{
	try{	
	src= new File(Product_Constants.ExcelFilePath+Product_Constants.ExcelFileName);
	fis=new FileInputStream(src);
	workbook=new XSSFWorkbook(fis);
	sheet=workbook.getSheet(Product_Constants.ExcelSheetName);
	//System.out.println(sheet.getRow(2).getCell(0).getStringCellValue());
	//System.out.println(sheet.getLastRowNum());
	for(int i=1;i<=sheet.getLastRowNum();i++)
	{
		int j=1;
		LogUtil.info("<<=====================================Executing Test Case ID = "+sheet.getRow(i).getCell(0).getStringCellValue()+ "=======================================================>>");
			if(!(sheet.getRow(i).getCell(j).getStringCellValue().equals("")))
				BaseVariables.jobtitle = sheet.getRow(i).getCell(j).getStringCellValue();
			//else if(sheet.getRow(i).getCell(j).getStringCellValue().equals(null))
				//BaseVariables.jobtitle = "";
			else
				BaseVariables.jobtitle = "";
			if(!(sheet.getRow(i).getCell(j+1).getStringCellValue().equals("")))
				BaseVariables.location = sheet.getRow(i).getCell(j+1).getStringCellValue();
			//else if(sheet.getRow(i).getCell(j+1).getStringCellValue().equals(null))
				//BaseVariables.location = "";
			else
				BaseVariables.location = "";
			LogUtil.info("Getting Job Title = "+BaseVariables.jobtitle + "\n" + "Getting Location = " +BaseVariables.location);
			Boolean result = getinstance().logic(BaseVariables.jobtitle, BaseVariables.location);
			if(result)
				BaseVariables.TestResult="Pass";
			else
				BaseVariables.TestResult="Fail";
			ExcelReadWrite.writeonExcel(BaseVariables.TestResult, i);
			LogUtil.info("<<=====================================End of Test Case ID = "+sheet.getRow(i).getCell(0).getStringCellValue()+ "Test Result = "+ BaseVariables.TestResult +" =======================================================>>");	
	}
	}
	catch(Exception e)
	{
		LogUtil.error("Error Occurred in Executing <<readFromExcel>> Methode, Exception = " + e.getMessage());
	}
	SendReport.getinstance().sendfinalrepot();
	//SendJavaxmailReport.getinstance().sendreport();
	
}
	//====================================================================================================================
	public static void writeonExcel(String result, int rownum)
	{
		try
		{	
			sheet.getRow(rownum).createCell(3).setCellValue(result);
			FileOutputStream output=new FileOutputStream(Product_Constants.ExcelFilePath+Product_Constants.ExcelFileName);
			workbook.write(output);
		}
		catch(Exception e)
		{
			//LogUtil.error("Error Occurred in Executing <<WriteonExcel>> Methode, Exception = " + e.getMessage());
		}
	}
}
