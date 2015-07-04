package com.verizon.bs.utils;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimeZone;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LoadFileData 
{
public static void readXlsx(File inputFile,PreparedStatement ps) 
{
try 
{
	ArrayList data = new ArrayList();
        // Get the workbook instance for XLSX file
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(inputFile));

        // Get first sheet from the workbook
        XSSFSheet sheet = wb.getSheetAt(0);

        Row row;
        Cell cell;

        // Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) 
        {
                row = rowIterator.next();
                row = rowIterator.next();

                // For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                int x = 0;
                while (cellIterator.hasNext()) 
                {
                	x++;
                cell = cellIterator.next();
                if(x ==1)
                {
                	cell.setCellType(cell.CELL_TYPE_STRING);
                	data.add(cell.getStringCellValue());
                    System.out.println(cell.getStringCellValue());
                }
                else
                {
                	if (DateUtil.isCellDateFormatted(cell)) {
                		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                		data.add(sdf.format(cell.getDateCellValue()));
                		System.out.println(DateUtil.getJavaDate(cell.getNumericCellValue(), TimeZone.getDefault()));
                		System.out.println(sdf.format(cell.getDateCellValue()));
                	}
                	else
                	{
                		cell.setCellType(cell.CELL_TYPE_STRING);
                    	data.add(cell.getStringCellValue());
                        System.out.println(cell.getStringCellValue());
                	}
                }
                	
                /*switch (cell.getCellType()) 
                {

                case Cell.CELL_TYPE_BOOLEAN:
                        System.out.println(cell.getBooleanCellValue());
                        break;
                

                case Cell.CELL_TYPE_NUMERIC:
                        System.out.println(cell.getNumericCellValue());
                        break;

                case Cell.CELL_TYPE_STRING:
                        System.out.println(cell.getStringCellValue());
                        break;

                case Cell.CELL_TYPE_BLANK:
                        System.out.println(" ");
                        break;

                default:
                        System.out.println(cell);
                }*/
            }
            
            System.out.println("--------------- " + data.get(0).toString());
            System.out.println(data.get(1).toString());
            System.out.println(data.get(2).toString());
            System.out.println(data.get(3).toString());
            System.out.println(data.get(4).toString());
            
            ps.setString(1, data.get(0).toString());
            ps.setString(2, data.get(1).toString());
            ps.setString(3, data.get(2).toString());
            ps.setString(4, data.get(3).toString());
            ps.setString(5, data.get(4).toString());
            ps.setString(6, null);
            ps.executeUpdate();
        }
}
catch (Exception e) 
{
        System.err.println("Exception :" + e.getMessage());
}
}

public static void readXls(File inputFile,PreparedStatement ps) 
{
try 
{
	ArrayList data = new ArrayList();
        // Get the workbook instance for XLS file
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(inputFile));
        // Get first sheet from the workbook
        HSSFSheet sheet = workbook.getSheetAt(0);
        Cell cell;
        Row row;

        // Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();
        
        while (rowIterator.hasNext()) 
        {
        	
                row = rowIterator.next();
                row = rowIterator.next();

                // For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                int x = 0;
                while (cellIterator.hasNext()) 
                {
                	x++;
                cell = cellIterator.next();
                if(x ==1)
                {
                	cell.setCellType(cell.CELL_TYPE_STRING);
                	data.add(cell.getStringCellValue());
                    System.out.println(cell.getStringCellValue());
                }
                else
                {
                	if (DateUtil.isCellDateFormatted(cell)) {
                		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                		data.add(sdf.format(cell.getDateCellValue()));
                		System.out.println(DateUtil.getJavaDate(cell.getNumericCellValue(), TimeZone.getDefault()));
                		System.out.println(sdf.format(cell.getDateCellValue()));
                	}
                	else
                	{
                		cell.setCellType(cell.CELL_TYPE_STRING);
                    	data.add(cell.getStringCellValue());
                        System.out.println(cell.getStringCellValue());
                	}
                }
                	
                /*switch (cell.getCellType()) 
                {

                case Cell.CELL_TYPE_BOOLEAN:
                        System.out.println(cell.getBooleanCellValue());
                        break;
                

                case Cell.CELL_TYPE_NUMERIC:
                        System.out.println(cell.getNumericCellValue());
                        break;

                case Cell.CELL_TYPE_STRING:
                        System.out.println(cell.getStringCellValue());
                        break;

                case Cell.CELL_TYPE_BLANK:
                        System.out.println(" ");
                        break;

                default:
                        System.out.println(cell);
                }*/
            }
            
            System.out.println("--------------- " + data.get(0).toString());
            System.out.println(data.get(1).toString());
            System.out.println(data.get(2).toString());
            System.out.println(data.get(3).toString());
            System.out.println(data.get(4).toString());
            
            ps.setString(1, data.get(0).toString());
            ps.setString(2, data.get(1).toString());
            ps.setString(3, data.get(2).toString());
            ps.setString(4, data.get(3).toString());
            ps.setString(5, data.get(4).toString());
            ps.setString(6, null);
            ps.executeUpdate();
        }

} 

catch (FileNotFoundException e) 
{
	e.printStackTrace();
        System.err.println("Exception" + e.getMessage());
}
catch (IOException e) 
{
	e.printStackTrace();
        System.err.println("Exception" + e.getMessage());
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}

public static void main(String[] args) 
{
        File inputFile = new File("C:\\testdata.xls");
        //File inputFile2 = new File("C:\\input.xlsx");
       // readXls(inputFile);
        //readXlsx(inputFile2);
}
}