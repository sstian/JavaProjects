package com.snow.env;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.connector.file.src.reader.TextLineInputFormat;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.time.Duration;

public class EnvDemo {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set(RestOptions.BIND_PORT, "8082");

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(conf);
        env.setRuntimeMode(RuntimeExecutionMode.STREAMING);

        // 使用文件源，持续监控文件变化
        FileSource<String> fileSource = FileSource
                .forRecordStreamFormat(new TextLineInputFormat(), new Path("input/word.txt"))
                .monitorContinuously(Duration.ofSeconds(10))  // 每10秒检查一次文件变化
                .build();

        DataStreamSource<String> lineDS = env.fromSource(fileSource, WatermarkStrategy.noWatermarks(), "file-source");

        lineDS
                .flatMap((String value, Collector<Tuple2<String, Integer>> out) -> {
                    String[] words = value.split(" ");
                    for (String word : words) {
                        out.collect(Tuple2.of(word, 1));
                    }
                })
                .returns(Types.TUPLE(Types.STRING, Types.INT))
                .keyBy(value -> value.f0)
                .sum(1)
                .print();

        env.execute("Continuous Word Count");


    }
}

/*
对于 IntelliJ IDEA：
1. 点击运行配置的编辑按钮
2. 在 "VM options" 或 "Modify options" 中添加以下JVM参数：
--add-opens java.base/java.lang=ALL-UNNAMED
--add-opens java.base/java.util=ALL-UNNAMED
 */
