package com.iiitb.academic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication//(exclude={DataSourceAutoConfiguration.class})
public class AcademicApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademicApplication.class, args);
	}

}
