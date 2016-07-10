package com.wise2c.client;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

@Component
class DiscoveryClientExample implements CommandLineRunner {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public void run(String... strings) throws Exception {
        discoveryClient.getInstances("photo-service").forEach((ServiceInstance s) -> {
            System.out.println(ToStringBuilder.reflectionToString(s));
        });
        discoveryClient.getInstances("bookmark-service").forEach((ServiceInstance s) -> {
            System.out.println(ToStringBuilder.reflectionToString(s));
        });
    }
}
