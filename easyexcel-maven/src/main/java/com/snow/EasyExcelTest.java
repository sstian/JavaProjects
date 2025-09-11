package com.snow;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class EasyExcelTest {

    // 通用数据生成 后面不会重复写
    private List<DemoData> data() {
        List<DemoData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }

    // 最简单的写
    @Test
    public void simpleWrite() {
        // 写法1
        String fileName = "EasyExcel.xlsx";
        // 这里需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());
    }

    // 最简单的读
    @Test
    public void simpleRead() {
        String fileName = "EasyExcel.xlsx";
        // 这里需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
    }
/*
[main] INFO com.snow.DemoDataListener - 解析到一条数据:{"date":"2025-09-11 18:20:47","doubleData":0.56,"string":"字符串0"}
[main] INFO com.snow.DemoDataListener - 解析到一条数据:{"date":"2025-09-11 18:20:47","doubleData":0.56,"string":"字符串1"}
[main] INFO com.snow.DemoDataListener - 解析到一条数据:{"date":"2025-09-11 18:20:47","doubleData":0.56,"string":"字符串2"}
[main] INFO com.snow.DemoDataListener - 解析到一条数据:{"date":"2025-09-11 18:20:47","doubleData":0.56,"string":"字符串3"}
[main] INFO com.snow.DemoDataListener - 解析到一条数据:{"date":"2025-09-11 18:20:47","doubleData":0.56,"string":"字符串4"}
[main] INFO com.snow.DemoDataListener - 解析到一条数据:{"date":"2025-09-11 18:20:47","doubleData":0.56,"string":"字符串5"}
[main] INFO com.snow.DemoDataListener - 解析到一条数据:{"date":"2025-09-11 18:20:47","doubleData":0.56,"string":"字符串6"}
[main] INFO com.snow.DemoDataListener - 解析到一条数据:{"date":"2025-09-11 18:20:47","doubleData":0.56,"string":"字符串7"}
[main] INFO com.snow.DemoDataListener - 解析到一条数据:{"date":"2025-09-11 18:20:47","doubleData":0.56,"string":"字符串8"}
[main] INFO com.snow.DemoDataListener - 解析到一条数据:{"date":"2025-09-11 18:20:47","doubleData":0.56,"string":"字符串9"}
[main] INFO com.snow.DemoDataListener - 10条数据，开始存储数据库！
[main] INFO com.snow.DemoDataListener - 存储数据库成功！
[main] INFO com.snow.DemoDataListener - 所有数据解析完成！
 */
}
