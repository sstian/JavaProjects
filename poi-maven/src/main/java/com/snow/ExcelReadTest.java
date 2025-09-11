package com.snow;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class ExcelReadTest {

    // 读取不同的数据类型
    @Test
    public void testCellType() throws IOException {
        InputStream is = new FileInputStream("会员消费商品明细表.xls");
        Workbook workbook = new HSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);

        // 读取标题所有内容
        Row rowTitle = sheet.getRow(0);
        if (rowTitle != null) {// 行不为空
            // 读取cell
            int cellCount = rowTitle.getPhysicalNumberOfCells();
            for (int cellNum = 0; cellNum < cellCount; cellNum++) {
                Cell cell = rowTitle.getCell(cellNum);
                if (cell != null) {
                    CellType cellType = cell.getCellType();
                    String cellValue = cell.getStringCellValue();
                    System.out.print(cellValue + "|");
                }
            }
            System.out.println();
        }

        // 读取商品列表数据
        int rowCount = sheet.getPhysicalNumberOfRows();
        for (int rowNum = 1; rowNum < rowCount; rowNum++) {
            Row rowData = sheet.getRow(rowNum);
            if (rowData != null) {// 行不为空
                // 读取cell
                Assertions.assertNotNull(rowTitle);
                int cellCount = rowTitle.getPhysicalNumberOfCells();
                for (int cellNum = 0; cellNum < cellCount; cellNum++) {
                    System.out.print("【" + (rowNum + 1) + "-" + (cellNum + 1) + "】");
                    Cell cell = rowData.getCell(cellNum);
                    if (cell != null) {
                        CellType cellType = cell.getCellType();
                        //判断单元格数据类型
                        String cellValue = "";
                        switch (cellType) {
                            case CellType.STRING://字符串
                                System.out.print("【STRING】");
                                cellValue = cell.getStringCellValue();
                                break;
                            case CellType.BOOLEAN://布尔
                                System.out.print("【BOOLEAN】");
                                cellValue = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case CellType.BLANK://空
                                System.out.print("【BLANK】");
                                break;
                            case CellType.NUMERIC:
                                System.out.print("【NUMERIC】");
                                //cellValue = String.valueOf(cell.getNumericCellValue());
                                if (DateUtil.isCellDateFormatted(cell)) {// 日期
                                    System.out.print("【日期】");
                                    Date date = cell.getDateCellValue();
                                    cellValue = new DateTime(date).toString("yyyy-MM-dd");
                                } else {
                                    // 不是日期格式，则防止当数字过长时以科学计数法显示
                                    System.out.print("【转换成字符串】");
                                    cell.setCellType(CellType.STRING);
                                    cellValue = cell.toString();
                                }
                                break;
                            case CellType.ERROR:
                                System.out.print("【数据类型错误】");
                                break;
                        }
                        System.out.println(cellValue);
                    }
                }
            }
        }
        is.close();
    }
/*
2025-09-11T09:36:28.004748300Z main ERROR Log4j API could not find a logging provider.
序号|卡号|持卡人|手机号|消费日期|小票号|商品编号|商品条码|商品名称|商品单位|原价|销售价|销售数量|销售金额|优惠金额|是否上架|
【2-1】【STRING】1
【2-2】【STRING】100088
【2-3】【STRING】狂神说
【2-4】【STRING】12333333333
【2-5】【NUMERIC】【日期】2020-04-21
【2-6】【STRING】0000201510200146
【2-7】【STRING】PV700012
【2-8】【STRING】PV700012
【2-9】【STRING】蒙牛
【2-10】【STRING】瓶
【2-11】【NUMERIC】【转换成字符串】200.5
【2-12】【NUMERIC】【转换成字符串】1000
【2-13】【STRING】1.0
【2-14】【NUMERIC】【转换成字符串】900
【2-15】【NUMERIC】【转换成字符串】100
【2-16】【BOOLEAN】true
【3-1】【STRING】2
【3-2】【NUMERIC】【转换成字符串】100089
【3-3】【STRING】秦疆
【3-4】【STRING】12333333333
【3-5】【NUMERIC】【日期】2020-04-21
【3-6】【STRING】0000201510200146
【3-7】【STRING】PV700006
【3-8】【STRING】PV700006
【3-9】【STRING】老白金
【3-10】【STRING】盒
【3-11】【NUMERIC】【转换成字符串】368.18
【3-12】【NUMERIC】【转换成字符串】20000
【3-13】【STRING】1.0
【3-14】【NUMERIC】【转换成字符串】20000
【3-15】【BLANK】
【3-16】【BOOLEAN】false
 */

}
