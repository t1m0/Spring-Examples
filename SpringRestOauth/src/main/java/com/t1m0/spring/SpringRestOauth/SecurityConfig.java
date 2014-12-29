package com.t1m0.spring.SpringRestOauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import com.t1m0.spring.SpringRestOauth.services.interfaces.LIUser;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated();
    http.formLogin().loginPage("/login").permitAll().and().logout().permitAll();
    http.httpBasic();
  }

  @Override
  public void configure(final WebSecurity webSecurity) throws Exception {
    webSecurity.ignoring().antMatchers("/webjars/**");
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Configuration
  protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    private LIUser repo;

    @Override
    public void init(final AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(repo);
    }
  }
}
