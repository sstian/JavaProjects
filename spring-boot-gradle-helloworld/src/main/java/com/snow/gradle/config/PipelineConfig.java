package com.snow.gradle.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "pipeline")
public class PipelineConfig {

    private String defaultType;
    private Map<String, List<String>> dict;

    @PostConstruct
    public void testInit() {
        System.out.println(defaultType);
        System.out.println(dict);
    }

}
