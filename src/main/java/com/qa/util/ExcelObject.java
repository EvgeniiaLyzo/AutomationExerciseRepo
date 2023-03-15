package com.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.qa.data.DataClass;

import org.apache.poi.ss.usermodel.DataFormatter;

public class ExcelObject {

    private XSSFSheet excelSheet;
    private XSSFWorkbook excelBook;
    private String pathOfExcel;
    private DataFormatter format = new DataFormatter();

    // constructor
    public ExcelObject(final String pathOfExcel, final String sheetName) throws IOException {
        this.pathOfExcel = pathOfExcel;
        try (FileInputStream ExcelFile = new FileInputStream(pathOfExcel)) {
            this.excelBook = new XSSFWorkbook(ExcelFile);
            this.excelSheet = this.excelBook.getSheet(sheetName);
        } catch (FileNotFoundException e) {
            throw (e);
        }
    }

    // get total number of rows
    public int getNumberOfRows() throws Exception {
        int result = 0;
        try {
            result = this.excelSheet.getPhysicalNumberOfRows();
//			result = excelSheet.getLastRowNum();
        } catch (Exception e) {
            System.out.println("Unable to get Number of rows");
            throw e;
        }
        return result;
    }

    public int getNumberOfColumns() {
        try {
            return excelSheet.getRow(0).getLastCellNum();
        } catch (Exception e) {
            System.out.println("Unable to get Number of columns");
            throw e;
        }
    }

    // get value from cell
    public String getCellValue(final int rownum, final int colnum) throws Exception {
        try {
            String cellValue = format.formatCellValue(excelSheet.getRow(rownum).getCell(colnum));
            return cellValue;
        } catch (Exception e) {
            throw e;
        }
    }

    // set value in sheet
    public void setCellValue(final String value, final int rownum, final int colnum) {
        try (FileOutputStream fos = new FileOutputStream(new File(pathOfExcel))) {
            XSSFRow row = excelSheet.getRow(rownum);
            XSSFCell cell = row.getCell(colnum);

            if (cell == null) {
                cell = excelSheet.getRow(rownum).createCell(colnum);
            }
            cell.setCellValue(value);
            excelBook.write(fos);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Object[][] getData() {
        Object[][] data = null;
        try {
            int rowNum = this.getNumberOfRows();
            int colNum = this.getNumberOfColumns();

            data = new Object[rowNum - 1][colNum];
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < colNum; j++) {
                    data[i][j] = this.getCellValue(i + 1, j);
                    String s = (String) data[i][j];
                }
            }

        } catch (Exception e) {
            System.out.println("Something wrong when transforming data to array");
            e.printStackTrace();
        } finally {
            this.close();
        }

        return data;
    }

    public void close() {
        try {
            this.excelBook.close();
        } catch (Exception e) {
            System.out.println("Something wrong with closing workbook");
            e.printStackTrace();
        }
    }

    public XSSFWorkbook getBook() {
        return this.excelBook;
    }

    public XSSFSheet getSheet() {
        return this.excelSheet;
    }

    public XSSFRow getRow(int rowNumber) {
        return excelSheet.getRow(rowNumber);
    }

}
