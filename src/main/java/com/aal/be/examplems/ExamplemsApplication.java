package com.aal.be.examplems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@RefreshScope
@EnableConfigurationProperties
@SpringBootApplication
@EnableDiscoveryClient
public class ExamplemsApplication {

	public static void main(String[] args) {
		 SpringApplication.run(ExamplemsApplication.class, args);
	}
}
