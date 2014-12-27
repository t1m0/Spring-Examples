package com.t1m0.spring.SpringJPA.junit;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.t1m0.spring.SpringJPA.Application;
import com.t1m0.spring.SpringJPA.entities.Todo;
import com.t1m0.spring.SpringJPA.persistence.interfaces.TodoRepo;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class TodoRepoTest {

  @Autowired
  TodoRepo      repo;
  
  @PersistenceContext
  EntityManager entityManager;
  
  @Test
  public void testSave() {
    Todo todo = new Todo("test", "test description");
    repo.save(todo);
    Query query = entityManager.createNativeQuery("select * from tbl_todos", Todo.class);
    List<Todo> todos = query.getResultList();
    assertEquals(1, todos.size());
  }

  @Test
  public void testCustomQuery() {
    Query query = entityManager
        .createNativeQuery("insert into tbl_todos (id,name,description) values (:id,:name,:desc)");
    query.setParameter("id", 100);
    query.setParameter("name", "testTodo");
    query.setParameter("desc", "bla bla bla");
    query.executeUpdate();
    Todo todo = repo.findByName("testTodo");
    assertEquals("bla bla bla", todo.getDescription());
  }
}
