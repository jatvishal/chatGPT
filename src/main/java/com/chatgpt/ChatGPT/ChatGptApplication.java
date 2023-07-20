package com.chatgpt.ChatGPT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ChatGptApplication {

	public static void main(String[] args) {

		SpringApplication.run(ChatGptApplication.class, args);
	}

}
