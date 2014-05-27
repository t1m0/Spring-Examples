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
package com.t1m0.SpringJPA.persistence.interfaces;

import java.util.List;

import com.t1m0.SpringJPA.entities.AEntity;

// TODO: Auto-generated Javadoc
/**
 * The generic CRUD Interface, which will be implemented by the DAOs.
 *
 * @param <T>
 *            the generic type
 */
public interface CRUD<T extends AEntity> {
	
	/**
	 * Persist the entity.
	 *
	 * @param a
	 *            the entity
	 * @return the persisted entity
	 */
	T create(T a);

	/**
	 * Receives the request entity from the persistence context.
	 *
	 * @param id
	 *            the identifier for the requested entity 
	 * @return the requested entity
	 */
	T read(long id);

	/**
	 * Update the given entity.
	 *
	 * @param t
	 *            the entity to update
	 * @return the updated entity
	 */
	AEntity update(T t);

	/**
	 * Delete the given entity.
	 *
	 * @param a
	 *            the entity to delete
	 */
	void delete(T a);

	/**
	 * Receives all entities present in the persistence context.
	 *
	 * @param start
	 *            the row-number to start with
	 * @param pagination
	 *            the amount of entities to receive
	 * @return the list< t> of all entities
	 */
	List<T> all(int start, int pagination);
}
