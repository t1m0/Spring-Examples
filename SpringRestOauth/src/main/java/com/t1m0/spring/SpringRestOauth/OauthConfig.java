package com.t1m0.spring.SpringRestOauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import com.t1m0.spring.SpringRestOauth.services.interfaces.LIClient;

@Configuration
public class OauthConfig {

  @Configuration
  @EnableResourceServer
  protected static class ResourceServer extends ResourceServerConfigurerAdapter {
    
    @Override
    public void configure(final HttpSecurity http) throws Exception {
      // @formatter:off
      http
      .requestMatchers().antMatchers("/rest/**").and()
      .authorizeRequests()
      .anyRequest().access("#oauth2.hasScope('read')");
      // @formatter:on
    }
    
    @Override
    public void configure(final ResourceServerSecurityConfigurer resources) throws Exception {
      resources.resourceId("alertserver");
    }
    
  }
  
  @Configuration
  @EnableAuthorizationServer
  protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {
    
    @Autowired
    private LIClient              clientRepo;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
      endpoints.authenticationManager(authenticationManager);
    }
    
    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
      clients.withClientDetails(clientRepo);
    }
    
  }
}
