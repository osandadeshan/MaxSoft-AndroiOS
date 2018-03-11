package com.maxsoft.androios.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.maxsoft.androios.util.GetCellAddressInExcel.baseObj;


/**
 * Created by Osanda on 7/14/2017.
 */


public abstract class ReadLocatorsFromExcel {

    //private static final String LOCATORS_FILE_PATH = System.getProperty("user.dir") + "\\" + System.getenv("locators_file_path");
    private static String value = "";

    public static String getDataFromExcel(String sheetName, int row, int column) {
        try {
            FileInputStream excelFile = new FileInputStream(new File(baseObj.getLocatorFilePath()));
            System.out.println(baseObj.getLocatorFilePath());
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet workSheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));
            value = workSheet.getRow(row).getCell(column).getStringCellValue();
            System.out.println(value);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String getElementName(String sheetName, String elementName) throws IOException {
        int row, column;
        row = GetCellAddressInExcel.findRowNumber(sheetName, elementName);
        column = GetCellAddressInExcel.findColumnNumber(sheetName, elementName);
        return getDataFromExcel(sheetName, row, column);
    }

    public static String getAndroidHow(String sheetName, String elementName) throws IOException {
        int row, column;
        row = GetCellAddressInExcel.findRowNumber(sheetName, elementName);
        column = GetCellAddressInExcel.findColumnNumber(sheetName, elementName) + 1;
        return getDataFromExcel(sheetName, row, column);
    }

    public static String getAndroidLocator(String sheetName, String elementName) throws IOException {
        int row, column;
        row = GetCellAddressInExcel.findRowNumber(sheetName, elementName);
        column = GetCellAddressInExcel.findColumnNumber(sheetName, elementName) + 2;
        return getDataFromExcel(sheetName, row, column);
    }

    public static String getIosHow(String sheetName, String elementName) throws IOException {
        int row, column;
        row = GetCellAddressInExcel.findRowNumber(sheetName, elementName);
        column = GetCellAddressInExcel.findColumnNumber(sheetName, elementName) + 3;
        return getDataFromExcel(sheetName, row, column);
    }

    public static String getIosLocator(String sheetName, String elementName) throws IOException {
        int row, column;
        row = GetCellAddressInExcel.findRowNumber(sheetName, elementName);
        column = GetCellAddressInExcel.findColumnNumber(sheetName, elementName) + 4;
        return getDataFromExcel(sheetName, row, column);
    }


}