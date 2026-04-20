package utils;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {
    private static final String path="C:\\Users\\2479652\\IdeaProjects\\IntermProject\\src\\main\\Resources\\addExcel.xlsx";


    public static void createWorkbook() throws IOException {
        XSSFWorkbook wb=new XSSFWorkbook();
        XSSFSheet sheet=wb.createSheet("Sheet1");
        XSSFRow row1 = sheet.createRow(0);
        String[] header={"Browser","URL","Search_Item","Product_1","Product2","Expected_Amount","Actual_Amount","Status"};
        for(int i=0;i<header.length;i++){
            row1.createCell(i).setCellValue(header[i]);
        }

        XSSFRow row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue("Chrome");
        row2.createCell(1).setCellValue("https://www.flipkart.com/");
        row2.createCell(2).setCellValue("Home appliances");

        try(FileOutputStream fos=new FileOutputStream(path)){
                wb.write(fos);
        }
        wb.close();

    }

    public static String readCell(int col) throws IOException {
        try (FileInputStream fis = new FileInputStream(path);
             XSSFWorkbook wb = new XSSFWorkbook(fis)) {
            return wb.getSheet("Sheet1").getRow(1).getCell(col).getStringCellValue();
        }
    }

    public static void writeResults(String product1Amt, String product2Amt,int expected, int actual, String status) throws IOException {

        XSSFWorkbook wb;
        try (FileInputStream fis = new FileInputStream(path)) {
            wb = new XSSFWorkbook(fis);
        }

        XSSFRow row = wb.getSheet("Sheet1").getRow(1);
        row.createCell(3).setCellValue(product1Amt);
        row.createCell(4).setCellValue(product2Amt);
        row.createCell(5).setCellValue(expected);
        row.createCell(6).setCellValue(actual);
        row.createCell(7).setCellValue(status);

        try (FileOutputStream fos = new FileOutputStream(path)) {
            wb.write(fos);
        }
        wb.close();

    }
}
