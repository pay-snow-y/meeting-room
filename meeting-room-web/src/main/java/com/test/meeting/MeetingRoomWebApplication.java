package com.test.meeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients(basePackages = { "com.test.meeting.service" })
@SpringBootApplication
public class MeetingRoomWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetingRoomWebApplication.class, args);
	}
}
