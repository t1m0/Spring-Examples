package com.t1m0.spring.SpringRestOauth.services.interfaces;

import org.springframework.security.oauth2.provider.ClientDetailsService;

import com.t1m0.spring.SpringRestOauth.entities.Client;

public interface LIClient extends CRUD<Client>, ClientDetailsService {

}
