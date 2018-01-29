package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadWrite {
	public static XSSFWorkbook workbook=null;
	public static File src=null;
	public static FileInputStream fis=null;
	public static XSSFSheet  sheet= null;
	public ArrayList<String> ExpectedURLs=new ArrayList<String>();
	public ArrayList<String> ActualURLs=new ArrayList<String>();
	public ArrayList<String> Result=new ArrayList<String>();
	public ArrayList<Double> Difference=new ArrayList<Double>();
	
	
	public void ReadFromExcel()
	{
		try{
			src=new File(System.getProperty("user.dir")+"\\Compare_URL.xlsx");
			//src=new File("C:\\Users\\mohit.sharma2\\workspace\\ImageComparision\\Compare_URL.xlsx");
			fis =new FileInputStream(src);
			workbook=new XSSFWorkbook(fis);
			sheet=workbook.getSheet("Data");
			LogsUtil.info("=====================================================================<<Fetching Data From Excel>> @Path : " + src +"=====================================================================");
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{
				if(sheet.getRow(i).getCell(1).getStringCellValue().equals("") && !sheet.getRow(i).getCell(2).getStringCellValue().equals("") || !sheet.getRow(i).getCell(1).getStringCellValue().equals("") && sheet.getRow(i).getCell(2).getStringCellValue().equals(""))
				{
				LogsUtil.error("Sheet Data is not Correct on " + (i+1) + " Row ");
				System.exit(1);
				}
				else
				{
				ExpectedURLs.add(sheet.getRow(i).getCell(1).getStringCellValue());
				ActualURLs.add(sheet.getRow(i).getCell(2).getStringCellValue());
				}
			}
			
		}
		catch(Exception e)
		{
			LogsUtil.error(e.getMessage());
		}
	}
	public void WriteonExcel()
	{
		try{
			for(int i=1; i<=Result.size();i++)
			{
			sheet.getRow(i).createCell(3).setCellValue(Result.get(i-1));
			if(Difference.get(i-1)!=0.0)
			{
			sheet.getRow(i).createCell(4).setCellValue(Difference.get(i-1).toString());	
			}
			FileOutputStream output=new FileOutputStream( System.getProperty("user.dir")+ "\\Compare_URL.xlsx");
			workbook.write(output);
			}	
			workbook.close();
		}
		catch(Exception e)
		{
			LogsUtil.error(e.getMessage());
		}
	}
}
