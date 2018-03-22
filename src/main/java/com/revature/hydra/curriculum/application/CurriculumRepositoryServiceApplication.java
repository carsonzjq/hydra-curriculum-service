package com.revature.hydra.curriculum.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.revature.hydra.curriculum.data.CurriculumRepository;
import com.revature.hydra.curriculum.service.CurriculumService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableJpaRepositories("com.revature.hydra.curriculum.data")
@SpringBootApplication
@Configuration
@EnableSwagger2
@ComponentScan(basePackages= {"com.revature.hydra.curriculum.controller", "com.revature.hydra.curriculum.service"})
@EntityScan("com.revature.beans")
public class CurriculumRepositoryServiceApplication {
	@Autowired
	CurriculumService curriculumService;
	
	@Autowired
	CurriculumRepository curriculumRepository;

	public static void main(String[] args) {
		SpringApplication.run(CurriculumRepositoryServiceApplication.class, args);
	}
	
	
}
