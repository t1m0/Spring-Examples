package com.t1m0.spring.SpringRestOauth.services.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;

import com.t1m0.spring.SpringRestOauth.entities.Client;

public interface LIClient extends CrudRepository<Client, Long>, ClientDetailsService {
  
  @Override
  @Query("select c from Client c where c.clientId = :id")
  public ClientDetails loadClientByClientId(@Param("id") String clientId);
}
