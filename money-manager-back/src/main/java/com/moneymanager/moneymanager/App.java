package com.moneymanager.moneymanager;

import com.moneymanager.moneymanager.configuration.WebserverConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		LOG.info("Starting application");
		SpringApplication.run(WebserverConfiguration.class, args);
		LOG.info("Application started");
	}

}
