package com.t1m0.spring.SpringRestOauth.services.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.t1m0.spring.SpringRestOauth.entities.User;

public interface LIUser extends CRUD<User>, UserDetailsService {

}
