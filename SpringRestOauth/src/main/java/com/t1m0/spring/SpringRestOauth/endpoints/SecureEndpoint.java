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
package com.t1m0.spring.SpringRestOauth.endpoints;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.t1m0.spring.SpringRestOauth.entities.User;

/**
 * The secure rest end point.
 */
@RestController
@RequestMapping(value="/secure", produces=MediaType.APPLICATION_JSON_VALUE)
public class SecureEndpoint {

	/** The id_counter. */
	private long id_counter = 0;

	/** The todos. */
	@SuppressWarnings("serial")
	private Map<Long,User> users = new HashMap<Long,User>(){{
		put(id_counter,new User(id_counter, "timo"));
		put(++id_counter,new User(id_counter, "hans"));
		put(++id_counter,new User(id_counter, "peter"));
	}};

	/**
	 * Gets a list of all users.
	 *
	 * @return the list of users
	 */
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Collection<User>> getListUser() {
		return new ResponseEntity<Collection<User>>(users.values(),HttpStatus.OK);
	}

	/**
	 * Gets the requested user.
	 *
	 * @param uid
	 *            the uid
	 * @return the user
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{uid}")
	public ResponseEntity<User> getUser(@PathVariable long uid) {
		return new ResponseEntity<User>(users.get(uid),HttpStatus.OK);
	}

	/**
	 * Adds the given user.
	 *
	 * @param t
	 *            the t
	 * @return the response entity< user>
	 */
	@RequestMapping(method = RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<User> addUser(@RequestBody User t) {
		t.setUID(++id_counter);
		users.put(id_counter, t);
		return new ResponseEntity<User>(t,HttpStatus.CREATED);
	}

	/**
	 * Deletes the given user.
	 *
	 * @param uid
	 *            the uid
	 * @return the response entity< user>
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{uid}")
	ResponseEntity<User> deleteUser(@PathVariable long uid) {
		return new ResponseEntity<User>(users.remove(uid), HttpStatus.NOT_FOUND);
	}
}
