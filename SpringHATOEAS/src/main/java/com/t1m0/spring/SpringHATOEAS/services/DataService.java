package com.t1m0.spring.SpringHATOEAS.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.t1m0.spring.SpringHATOEAS.entities.*;

@Component
public class DataService implements IDataService{
	
	private long id_counter = 0;
	
	/** The todos. */
	private Map<Long,Todo> todos = new HashMap<Long,Todo>();
	
	/** The users. */
	@SuppressWarnings("serial")
	private Map<Long,User> users = new HashMap<Long,User>();
	
	@PostConstruct
	private void init(){
		User u = new User(id_counter, "timo");
		users.put(id_counter,u);
		Todo t0 = new Todo(++id_counter, "Test 0", "Test 0");
		t0.setUser(u);
		todos.put(id_counter, t0);
		Todo t1 = new Todo(++id_counter, "Test 1", "Test 1");
		t1.setUser(u);
		todos.put(++id_counter,t1);
		Todo t2 = new Todo(id_counter, "Test 2", "Test 2");
		t2.setUser(u);
		todos.put(++id_counter, t2);
	}

	@Override
	public Collection<Todo> allTodos() {
		return todos.values();
	}

	@Override
	public Collection<User> allUsers() {
		return users.values();
	}

	@Override
	public Todo addTodo(Todo t) {
		t.setUID(++id_counter);
		todos.put(id_counter, t);
		return t;
	}

	@Override
	public User addUser(User u) {
		u.setUID(++id_counter);
		users.put(id_counter, u);
		return u;
	}

	@Override
	public Todo getTodo(long uid) {
		return todos.get(uid);
	}

	@Override
	public User getUser(long uid) {
		return users.get(uid);
	}

	@Override
	public Todo updateTodo(Todo t) {
		todos.put(t.getUID(), t);
		return t;
	}

	@Override
	public User updateUser(User u) {
		users.put(u.getUID(), u);
		return u;
	}

	@Override
	public void removeTodo(long uid) {
		todos.remove(uid);
	}

	@Override
	public void removeUser(long uid) {
		users.remove(uid);
		
	}
}
