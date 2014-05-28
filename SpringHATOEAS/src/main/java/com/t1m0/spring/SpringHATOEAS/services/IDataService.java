package com.t1m0.spring.SpringHATOEAS.services;

import java.util.Collection;

import com.t1m0.spring.SpringHATOEAS.entities.*;

public interface IDataService {
	
	public Collection<Todo> allTodos();
	public Collection<User> allUsers();
	public Todo addTodo(Todo t);
	public User addUser(User u);
	public Todo getTodo(long uid);
	public User getUser(long uid);
	public Todo updateTodo(Todo t);
	public User updateUser(User u);
	public void removeTodo(long uid);
	public void removeUser(long uid);
}
