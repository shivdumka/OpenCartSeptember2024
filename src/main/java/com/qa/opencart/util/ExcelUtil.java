package com.qa.opencart.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil 
{
	
	private static Workbook book;
	private static Sheet sheet;
	
	//ALWAYS MAKE ALL THE IMPORTS OF APACHE POI FROM "org.apache.poi.ss......."
	
	public static Object[][] getTestData(String sheetName)
	{
		
		Object[][] data=null;
		
		try {
			//Telling our framework where to locate(find) the .xlsx file using fileinputstream class object and giving the path
			
			FileInputStream fis=new FileInputStream(".\\src\\test\\resource\\testdata\\TestData.xlsx");
			
			//Now create the workbook using apache poi in our framework
			
			book=WorkbookFactory.create(fis);
			
			//Now lets tell our framework from which sheet of the workbook to take or pick the data from
			
			sheet=book.getSheet(sheetName);
			
			//Now we are inside the sheets with the access to its rows and columns and we know DataProvider accepts data in 2D Object[][] form,so we will create....
			//.....2D Object[][] array for rows and columns
			
			data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			//Now we have an empty Object array and lets fill the array with iterating the sheet with rows*column
			
			//here while filling the data in the cells rememeber the outer loop is for the rows and inner loop is for the columns,columns are fetched using .getCell(j) 
			
			//and java doesnt understand the excel language format therfore we will convert all the data of excel in string while filling it using .toString()
			
			for(int i=0;i<=sheet.getLastRowNum()-1;i++)
			{
				for(int j=0;j<=sheet.getRow(0).getLastCellNum()-1;j++)
				{
					data[i][j]=sheet.getRow(i+1).getCell(j).toString();
				}
			}
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return data;
	}
	
}
