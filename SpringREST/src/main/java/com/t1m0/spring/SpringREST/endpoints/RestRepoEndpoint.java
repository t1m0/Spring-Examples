package com.t1m0.spring.SpringREST.endpoints;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.t1m0.spring.SpringREST.entities.Todo;

@RepositoryRestResource(collectionResourceRel = "todo", path = "todo")
public interface RestRepoEndpoint extends PagingAndSortingRepository<Todo, Long> {

}
