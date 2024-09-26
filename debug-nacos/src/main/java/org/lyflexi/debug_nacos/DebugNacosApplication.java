package org.lyflexi.debug_nacos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@Slf4j
@EnableFeignClients
@EnableDiscoveryClient
public class DebugNacosApplication {



	public static void main(String[] args) {

		SpringApplication.run(DebugNacosApplication.class, args);
	}

}
