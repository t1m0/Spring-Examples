package com.t1m0.spring.SpringRestOauth.services.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.t1m0.spring.SpringRestOauth.entities.User;

public interface LIUser extends CrudRepository<User, Long>, UserDetailsService {
  @Override
  @Query("select u from User u where u.username = :name")
  public UserDetails loadUserByUsername(@Param("name") String username);
}
