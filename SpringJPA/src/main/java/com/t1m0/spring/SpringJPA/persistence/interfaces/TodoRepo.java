package com.t1m0.spring.SpringJPA.persistence.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.t1m0.spring.SpringJPA.entities.Todo;

@Component
public interface TodoRepo extends CrudRepository<Todo, Long> {
  @Query("select t from Todo t where t.name = :name")
  Todo findByName(@Param("name") String name);
}
