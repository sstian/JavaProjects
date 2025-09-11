package com.snow;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWriteTest {
    /*
优点：可以写非常大的数据量，如100万条甚至更多条，写数据速度快，占用更少的内存

注意：
过程中会产生临时文件，需要清理临时文件
默认由100条记录被保存在内存中，如果超过这数量，则最前面的数据被写入临时文件
如果想自定义内存中数据的数量，可以使用new SXSSFWorkbook ( 数量 )

SXSSFWorkbook-来至官方的解释：实现“BigGridDemo”策略的流式XSSFWorkbook版本。这允许写入
非常大的文件而不会耗尽内存，因为任何时候只有可配置的行部分被保存在内存中。
请注意，仍然可能会消耗大量内存，这些内存基于您正在使用的功能，例如合并区域，注释......仍然只存
储在内存中，因此如果广泛使用，可能需要大量内存。
     */
    @Test
    public void testWriteBitData() throws IOException {
        //记录开始时间
        long begin = System.currentTimeMillis();
        System.out.println("start");

        //创建一个workbook
//        Workbook workbook = new XSSFWorkbook();
//        Workbook workbook = new HSSFWorkbook();
        Workbook workbook = new SXSSFWorkbook();
        //创建一个sheet
        Sheet sheet = workbook.createSheet();

        for (int rowNum = 0; rowNum < 100000; rowNum++) {
            //创建一个行
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 10; cellNum++) {
                //创建单元格
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }

        FileOutputStream out = new FileOutputStream("SXSSFWorkbook.xlsx");
        workbook.write(out);
        //操作结束，关闭文件
        out.close();
        //清除临时文件
        ((SXSSFWorkbook)workbook).dispose();

        //记录结束时间
        System.out.println("done");
        long end = System.currentTimeMillis();
        System.out.println((double)(end - begin)/1000);

    }
/*
[1.372s][error][attach] failure (232) writing result of operation jcmd to pipe \\.\pipe\javatool1931777189
start
2025-09-11T09:17:20.204527600Z main ERROR Log4j API could not find a logging provider.
done
5.262
 */
}
