package com.snow.gradle.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PipelineConfigTest {

    @Autowired
    private PipelineConfig pipelineConfig;

    @Test
    public void testConfig() {
    }
}