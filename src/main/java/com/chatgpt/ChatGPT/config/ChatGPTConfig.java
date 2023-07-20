package com.chatgpt.ChatGPT.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.persistence.*;

@Component
@EnableWebSecurity
@Configuration
@Entity
@Table(name = "chatgptdb")
public class ChatGPTConfig extends WebSecurityConfigurerAdapter {
    @Value("${chatgpt.key}")
    private String token;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "question")
    private String name;

    @Bean
    public RestTemplate getTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer "+ token);
            return execution.execute(request,body);
        });
        return restTemplate;
    }
    //disabling the CSRF token issue
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }
}
