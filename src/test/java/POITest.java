import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class POITest {
    @Test
    public void testRead() throws Exception {
        //1:创建基于2003版本的工作簿,从流中取出该Excel文件的数据
        Workbook wb = new HSSFWorkbook(new FileInputStream("d:/1.xls"));
        //2:获取纸张对象
        Sheet sheet = wb.getSheetAt(0);
        //3:从纸张对象中获取行
        System.out.println(sheet.getLastRowNum());
        /*Row row = sheet.getRow(0);
        //4:从行中获取细胞,从细胞中获取数据
        System.out.println(row.getLastCellNum());
        System.out.println(row.getCell(0).getStringCellValue());
        System.out.println(row.getCell(1).getStringCellValue());
        System.out.println(row.getCell(2).getStringCellValue());
        //=============================
        row = sheet.getRow(1);
        //4:从行中获取细胞,从细胞中获取数据
        System.out.println(row.getLastCellNum());
        System.out.println(row.getCell(0).getStringCellValue());
        System.out.println(row.getCell(1).getStringCellValue());
        System.out.println(row.getCell(2).getNumericCellValue());*/
    }

    @Test
    public void testWrite() throws Exception {
        //1:创建基于2003版本的工作簿
        Workbook wb = new HSSFWorkbook();
        //2:在工作簿中创建纸张
        Sheet sheet = wb.createSheet();
        //3:在纸张创建行
        Row row = sheet.createRow(0);
        //4:在行中创建细胞,设置数据
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("邮箱");
        row.createCell(2).setCellValue("年龄");
        //=================================
        row = sheet.createRow(1);
        row.createCell(0).setCellValue("逍遥");
        row.createCell(1).setCellValue("xiaoyao@163.com");
        row.createCell(2).setCellValue(18);
        //6:写到某个文件中
        wb.write(new FileOutputStream(new File("d:/1.xls")));
    }
}
