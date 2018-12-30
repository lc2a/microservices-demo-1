package com.learn;

import com.github.mongobee.Mongobee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ProductApplication {

	@Autowired
	private Environment env;


	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}


	@Bean
	public Mongobee mongobee(){
		String mongoDBURI = env.getProperty("spring.data.mongodb.uri");
		Mongobee runner = new Mongobee(mongoDBURI);
		runner.setChangeLogsScanPackage("com.learn.mongobee");

		return runner;
	}
}

