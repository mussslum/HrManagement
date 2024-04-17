package com.example.hrmanagement;

import com.example.hrmanagement.model.UserEntity;
import com.example.hrmanagement.model.Vacancy;
import com.example.hrmanagement.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class HrManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrManagementApplication.class, args);
	}

}
