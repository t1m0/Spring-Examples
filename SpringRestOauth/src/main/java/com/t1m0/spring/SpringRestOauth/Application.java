package com.t1m0.spring.SpringRestOauth;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.t1m0.spring.SpringRestOauth.entities.Client;
import com.t1m0.spring.SpringRestOauth.entities.User;
import com.t1m0.spring.SpringRestOauth.services.interfaces.LIClient;
import com.t1m0.spring.SpringRestOauth.services.interfaces.LIUser;

@EnableWebMvc
@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {
  
  @Autowired
  LIUser   userRepo;
  
  @Autowired
  LIClient clientRepo;
  
  public static void main(final String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }

  @PostConstruct
  public void postConstruct() {
    User u0 = new User("admin", "admin", User.Role.ROLE_ADMIN);
    userRepo.save(u0);
    User u1 = new User("user", "user", User.Role.ROLE_USER);
    userRepo.save(u1);
    @SuppressWarnings("serial")
    Set<String> scope = new HashSet<String>() {
      {
        add("read");
        add("write");
        add("trust");
      }
    };
    @SuppressWarnings("serial")
    Set<String> grantTypes = new HashSet<String>() {
      {
        add("password");
        add("authorization_code");
        add("refresh_token");
        add("implicit");
        add("client_credentials");
      }
    };
    Client c0 = new Client("clientWithSecret", "secret");
    c0.setAuthorizedGrantTypes(grantTypes);
    c0.setAuthorities(AuthorityUtils.createAuthorityList(User.Role.ROLE_ADMIN.name()));
    c0.setScoped(false);
    c0.setScope(scope);
    clientRepo.save(c0);
    Client c1 = new Client("web-client");
    c1.setAuthorizedGrantTypes(grantTypes);
    c1.setAuthorities(AuthorityUtils.createAuthorityList(User.Role.ROLE_USER.name()));
    c1.setScoped(false);
    c1.setScope(scope);
    clientRepo.save(c1);
  }
}
