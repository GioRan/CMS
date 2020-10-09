package com.cms.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@EnableConfigServer
@EnableEurekaServer
@SpringBootApplication
public class DiscoveryserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryserverApplication.class, args);
	}


	@Bean
	public Function<String, String> uppercase() {
		return value -> value.toUpperCase();
	}
}
