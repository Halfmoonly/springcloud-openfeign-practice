package org.lyflexi.customfeignclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan(
        value = "org.lyflexi",/*扫自身，加上扫common*/
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,pattern = "org.lyflexi.customfeignclient.feign.config.*"))
public class CustomFeignClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomFeignClientApplication.class, args);
    }

}
