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
package com.t1m0.spring.SpringJPA.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.t1m0.spring.SpringJPA.entities.Todo;
import com.t1m0.spring.SpringJPA.persistence.interfaces.LITodo;

/**
 * The Class TodoDAO persist Todo items.
 */
@Component(value=TodoDAO.BEAN_NAME)
@Transactional
public class TodoDAO implements LITodo {
	
	/** The Constant BEAN_NAME. */
	public static final String BEAN_NAME = "TodoDAO";
	
	/** The sql all. */
	private final String SQL_ALL = "SELECT c FROM Todo c";
	
	
	/** The manager. */
	@PersistenceContext
	private EntityManager manager;

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Todo create(final Todo a) {
		this.manager.persist(a);
		return a;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Todo read(final long id) {
		return this.manager.find(Todo.class, id);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Todo update(final Todo a) {
		this.manager.merge(a);
		return a;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Todo a) {
		this.manager.remove(a);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Todo> all(final int start, final int pagination) {
		Query q = this.manager.createQuery(this.SQL_ALL, Todo.class);
		if (start > 0) {
			q.setFirstResult(start);
		}
		q.setMaxResults(pagination);
		return q.getResultList();
	}

}
