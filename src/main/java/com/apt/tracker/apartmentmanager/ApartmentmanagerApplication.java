package com.apt.tracker.apartmentmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class ApartmentmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApartmentmanagerApplication.class, args);
	}

}
