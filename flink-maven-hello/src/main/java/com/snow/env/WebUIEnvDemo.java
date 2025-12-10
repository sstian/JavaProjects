package com.snow.env;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class WebUIEnvDemo {
    public static void main(String[] args) throws Exception {
        // 启用 Web UI
        Configuration conf = new Configuration();
        conf.set(RestOptions.ENABLE_FLAMEGRAPH, true);
        conf.set(RestOptions.PORT, 8081);

        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);

        // 创建无限数据流
        env.fromSequence(1, Long.MAX_VALUE)
                .map(i -> "word" + (i % 10))
                .flatMap((String value, Collector<Tuple2<String, Integer>> out) -> {
                    out.collect(Tuple2.of(value, 1));
                })
                .returns(Types.TUPLE(Types.STRING, Types.INT))
                .keyBy(value -> value.f0)
                .sum(1)
                .print();

        // 执行作业
        env.execute("Long Running Job");

        // 程序会一直运行，直到通过 Web UI 或 API 取消
    }
}