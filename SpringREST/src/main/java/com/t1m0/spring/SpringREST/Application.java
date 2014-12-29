package com.t1m0.spring.SpringREST;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.t1m0.spring.SpringREST.entities.Todo;

@EnableWebMvc
@SpringBootApplication
public class Application extends RepositoryRestMvcConfiguration {
  public static void main(final String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }

  @Override
  protected void configureRepositoryRestConfiguration(final RepositoryRestConfiguration config) {
    super.configureRepositoryRestConfiguration(config);
    config.exposeIdsFor(Todo.class);
    try {
      config.setBaseUri(new URI("/rest"));
    } catch (final URISyntaxException e) {
      e.printStackTrace();
    }
  }
}
