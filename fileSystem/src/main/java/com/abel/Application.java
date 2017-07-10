package com.abel;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan("com.abel")
@SpringBootApplication
public class Application {
	private static final Logger log = Logger.getLogger(Application.class);

	public static void main(String[] args) {
		try {
			SpringApplication app = new SpringApplication(Application.class);
			app.setWebEnvironment(true);
	        app.run(args);
		} catch (Exception e) {
			log.error("application main exception", e);
		}
	}

}
