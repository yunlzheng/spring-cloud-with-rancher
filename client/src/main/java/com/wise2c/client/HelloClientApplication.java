package com.wise2c.client;

import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
@EnableSwagger2
public class HelloClientApplication {
    @Autowired
    HelloClient client;


    @RequestMapping(value = "/", method = GET)
    public String hello() {
        return client.hello();
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloClientApplication.class, args);
    }

    @FeignClient("HelloServer")
    interface HelloClient {
        @RequestMapping(value = "/", method = GET)
        String hello();
    }


    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("hello")
                .apiInfo(apiInfo())
                .select()
                .paths(getPaths())
                .build();
    }

    private Predicate<String> getPaths() {
        return regex("/*");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("server API")
                .description("API Documents")
                .termsOfServiceUrl("")
                .license("Apache License Version 2.0")
                .version("2.0")
                .build();
    }
}