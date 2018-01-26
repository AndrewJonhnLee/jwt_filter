package com.cloud.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrixDashboard //开启hystrix dashboard visit http://localhost:8881/hystrix
//@EnableEurekaClient
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}
}
//
//SpringCLoud中的“Discovery Service”有多种实现，比如：eureka, consul, zookeeper。
//
//		1，@EnableDiscoveryClient注解是基于spring-cloud-commons依赖，并且在classpath中实现；
//		2，@EnableEurekaClient注解是基于spring-cloud-netflix依赖，只能为eureka作用；
//
//		如果你的classpath中添加了eureka，则它们的作用是一样的。