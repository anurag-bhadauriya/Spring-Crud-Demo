package com.examroom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ExamroomApplication {

	private static final Logger log = LoggerFactory.getLogger(ExamroomApplication.class);
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ExamroomApplication.class);
		Environment env = app.run(args).getEnvironment();
		logApplicationStartup(env);
	}

	private static void logApplicationStartup(Environment env) {
		String protocol = "http";
		String serverPort = env.getProperty("server.port");
		log.info("\n-----------------------------------------------------------\n\t"
				+"Application is running! Access URL's:\n\t"+"Local:\t\t{}://localhost:{}/\n\t"
				+"Profile(s): \t {}\n-----------------------------------------------------------",
				protocol, serverPort, env.getActiveProfiles());
	}
}
