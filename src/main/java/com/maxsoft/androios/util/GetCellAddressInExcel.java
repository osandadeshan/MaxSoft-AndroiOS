package com.maxsoft.androios.util;

import com.maxsoft.androios.common.Base;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;


/**
 * Created by Osanda on 7/17/2017.
 */


public abstract class GetCellAddressInExcel {

	//private static final String LOCATORS_FILE_PATH = System.getProperty("user.dir") + System.getenv("locators_file_path");
	static Base baseObj = new Base();
	static int column;
	
	public static int findColumnNumber(String sheetName, String cellContent) throws IOException {
		FileInputStream inputStream = new FileInputStream(baseObj.getLocatorFilePath());
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));
		Iterator<Row> iterator = firstSheet.iterator();
		CellAddress columnNumber = null;
		
		while(iterator.hasNext()){
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if(cell.getCellType()==Cell.CELL_TYPE_STRING){
					String text = cell.getStringCellValue();
					if (cellContent.equals(text)) {
						columnNumber=cell.getAddress();
						column = cell.getColumnIndex();
						break;
					}
				}
			}
		}
		workbook.close();
		return column;
	}
	
	public static int findRowNumber(String sheetName, String cellContent) throws IOException {
		System.out.println(baseObj.getLocatorFilePath());
		FileInputStream excelFile = new FileInputStream(new File(baseObj.getLocatorFilePath()));
		Workbook workbook = new XSSFWorkbook(excelFile);
		Sheet workSheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));
		for (Row row : workSheet) {
			for (Cell cell : row) {
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
						return row.getRowNum();
					}
				}
			}
		}
		return 0;
	}
	
	
}
