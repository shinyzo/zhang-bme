package com.zhang.bme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ServletComponentScan
@ComponentScan("com.zhang.bme")
public class BmeApplication {

	public static void main(String[] args) {

		SpringApplication.run(BmeApplication.class, args);

	}

	@Bean
	@PostConstruct
	public SpringContextUtil initSpringContextUtil(){
		return SpringContextUtil.getInstance();
	}

}
