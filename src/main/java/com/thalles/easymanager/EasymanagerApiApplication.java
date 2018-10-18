package com.thalles.easymanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.thalles.easymanager.config.property.EasyManagerApiProperty;


@SpringBootApplication
@EnableConfigurationProperties(EasyManagerApiProperty.class)
public class EasymanagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasymanagerApiApplication.class, args);
	}
}
