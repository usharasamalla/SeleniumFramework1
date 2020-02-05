package com.Utilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class ExcelFileUtil {
	Workbook wb;
	public ExcelFileUtil(String excelpath) throws Throwable
	{
		FileInputStream fi=new FileInputStream(excelpath);
		wb=new WorkbookFactory().create(fi);

	}
	public int rowCount(String sheetname)
	{

		return wb.getSheet(sheetname).getLastRowNum();
	}
	public int colCount(String shteetname)
	{

		return wb.getSheet(shteetname).getRow(0).getLastCellNum();
	}
	public String getCellData(String sheetname,int row,int coloumn)
	{
		String data="";
		if(wb.getSheet(sheetname).getRow(row).getCell(coloumn).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int cellData=(int)wb.getSheet(sheetname).getRow(row).getCell(coloumn).getNumericCellValue();
			data=String.valueOf(cellData);
		}
		else
		{
		data=wb.getSheet(sheetname).getRow(row).getCell(coloumn).getStringCellValue();
		}
		return data;
	}
	public void setCellData(String sheetname,int row,int column,String status ,String writeexcel)
			throws Throwable{
			Sheet ws=wb.getSheet(sheetname);
			Row rownum=ws.getRow(row);
			Cell cell=rownum.createCell(column);
			cell.setCellValue(status);
			if(status.equalsIgnoreCase("Pass"))
			{
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
			}
			else if(status.equalsIgnoreCase("fail"))
			{
				CellStyle style=wb.createCellStyle();
				Font font=wb.createFont();
				font.setColor(IndexedColors.RED.getIndex());
				font.setBold(true);
				font.setBoldweight(Font.BOLDWEIGHT_BOLD);
				style.setFont(font);
				rownum.getCell(column).setCellStyle(style);
			}
			else if(status.equalsIgnoreCase("blocked"))
			{
				CellStyle style=wb.createCellStyle();
				Font font=wb.createFont();
				font.setColor(IndexedColors.BLUE.getIndex());
				font.setBold(true);
				font.setBoldweight(Font.BOLDWEIGHT_BOLD);
				style.setFont(font);
				rownum.getCell(column).setCellStyle(style);
			}
			
	
    FileOutputStream fo=new FileOutputStream(writeexcel);
	 wb.write(fo);
	 fo.close();
 }

}

