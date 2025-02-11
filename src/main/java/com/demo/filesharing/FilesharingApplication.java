package com.demo.filesharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FilesharingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilesharingApplication.class, args);
	}

}
