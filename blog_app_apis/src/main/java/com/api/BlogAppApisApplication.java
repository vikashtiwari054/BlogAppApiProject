package com.api;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
//@EnableScheduling
public class BlogAppApisApplication {

	private static final Logger LOGGER=LoggerFactory.getLogger(BlogAppApisApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	
		LOGGER.info("blog application started.......");
		
	}
	@Bean
	public ModelMapper  modelMapper() {
		
		return new ModelMapper();
		
	}
	
}
