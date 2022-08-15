package com.INetwork.advancedfileoperation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AdvancedFileOperationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedFileOperationApplication.class, args);
	}

}
