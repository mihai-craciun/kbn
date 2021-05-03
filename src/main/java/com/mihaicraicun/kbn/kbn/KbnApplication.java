package com.mihaicraicun.kbn.kbn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class KbnApplication {

	@Bean
	HiddenHttpMethodFilter hiddenHttpMethodFilter() {
    	return new HiddenHttpMethodFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(KbnApplication.class, args);
	}

}
