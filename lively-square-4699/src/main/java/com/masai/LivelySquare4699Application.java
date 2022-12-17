package com.masai;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class LivelySquare4699Application {

	public static void main(String[] args) {
		SpringApplication.run(LivelySquare4699Application.class, args);
	}
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}