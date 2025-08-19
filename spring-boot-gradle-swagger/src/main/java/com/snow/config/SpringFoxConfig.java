package com.snow.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.net.URI;

/**
 * Swagger configuration class
 * @author Javen Tian
 * @since 2020/11/12
 */

@EnableOpenApi
@Configuration
public class SpringFoxConfig {

    @Value("${swagger.enabled}")
    private boolean swaggerEnabled;

    @Bean
    RouterFunction<ServerResponse> redirectRouterFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/"), request ->
                ServerResponse.temporaryRedirect(URI.create("/swagger-ui/index.html")).build());
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .enable(this.swaggerEnabled)
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket createRestApi2() {
        return new Docket(DocumentationType.OAS_30)
                .enable(this.swaggerEnabled)
                .groupName("great") // definition group, default: default
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot Gradle Swagger")
                .version("1.0")
                .description("RESTFul interface document")
                .termsOfServiceUrl("https://sstian.github.io/")
                .contact(new Contact("sstian",
                                    "https://sstian.github.io/",
                                        "sstian@snow.com"))
                .build();
    }
}