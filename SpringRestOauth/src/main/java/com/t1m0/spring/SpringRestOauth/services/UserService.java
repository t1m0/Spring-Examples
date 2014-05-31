/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Timo Schoepflin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.t1m0.spring.SpringRestOauth.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.t1m0.spring.SpringRestOauth.entities.User;
import com.t1m0.spring.SpringRestOauth.services.interfaces.LIUser;


/**
 * Custom implementation of {@link UserDetailsService}, to enable custom user handling.
 */
@Transactional
@Component(UserService.BEAN_NAME)
public class UserService implements LIUser {

	public final static String BEAN_NAME = "userService";

	/** The sql all. */
	private final String SQL_ALL = "SELECT u FROM User u";

	/** The sql all. */
	private final String SQL_BY_NAME = "select u from User u where u.username = :name";

	/** The manager. */
	@PersistenceContext
	private EntityManager manager;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User create(final User a) {
		manager.persist(a);
		return a;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User read(final long id) {
		return manager.find(User.class, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User update(final User a) {
		manager.merge(a);
		return a;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final User a) {
		manager.remove(a);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<User> all() {
		Query q = manager.createQuery(SQL_ALL, User.class);
		return q.getResultList();
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	@SuppressWarnings("unchecked")
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Query q = manager.createQuery(SQL_BY_NAME, User.class);
		q.setParameter("name",username);
		List<User> users = q.getResultList();
		if(users.size() == 0){
			throw new UsernameNotFoundException("Username '"+username+"' not found!");
		}
		return users.get(0);
	}

}
