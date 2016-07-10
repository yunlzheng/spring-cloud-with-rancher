package com.wise2c.server;

import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author Spencer Gibb
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@RestController
public class HelloServerApplication {
    @Autowired
    DiscoveryClient client;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello() {
        ServiceInstance localInstance = client.getLocalServiceInstance();
        return "Hello World: " + localInstance.getServiceId() + ":" + localInstance.getHost() + ":" + localInstance.getPort();
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloServerApplication.class, args);
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