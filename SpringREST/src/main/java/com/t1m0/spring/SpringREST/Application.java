package com.t1m0.spring.SpringREST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {
  public static void main(final String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }
}
