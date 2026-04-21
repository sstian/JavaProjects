package com.snow.drools.config;

import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 规则引擎配置类
 *
 * 说明：
 * - 定义了一个 `KieContainer`的`Spring Bean` ，`KieContainer`用于通过加载应用程序的`/resources`文件夹下的规则文件来构建规则引擎。
 * - 创建`KieFileSystem`实例并配置规则引擎并从应用程序的资源目录加载规则的 `DRL` 文件。
 * - 使用`KieBuilder`实例来构建 `drools` 模块。我们可以使用KieSerive单例实例来创建 `KieBuilder` 实例。
 * - 最后，使用 `KieService` 创建一个 `KieContainer` 并将其配置为 `spring bean`
 */
@Configuration
public class DroolsConfig {

    private static final KieServices kieServices = KieServices.Factory.get();
    //制定规则文件的路径
    private static final String RULES_CUSTOMER_RULES_DRL = "rules/order.drl";

    @Bean
    public KieContainer kieContainer() {
        //获得Kie容器对象
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_CUSTOMER_RULES_DRL));

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();

        KieModule kieModule = kieBuilder.getKieModule();
        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());

        return kieContainer;
    }

}