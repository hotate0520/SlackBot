package com.example.slackbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SlackBotApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SlackBotApplication.class, args);
	}

}
