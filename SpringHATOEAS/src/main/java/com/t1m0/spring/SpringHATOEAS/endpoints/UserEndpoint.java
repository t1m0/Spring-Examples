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
package com.t1m0.spring.SpringHATOEAS.endpoints;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.t1m0.spring.SpringHATOEAS.entities.User;
import com.t1m0.spring.SpringHATOEAS.services.IDataService;

/**
 * The rest end point for the resource {@link User}.
 */
@RestController
@RequestMapping(value="/user", produces=MediaType.APPLICATION_JSON_VALUE)
public class UserEndpoint {
	
	@Autowired
	IDataService ds;
	
	/**
	 * Gets a list of all users.
	 *
	 * @return the list of users
	 */
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Collection<User>> getListUser() {
		Collection<User> users = ds.allUsers();
		//Ensures, that each user has the hatoeas links
		users.forEach(u-> u.hatoeas());
		return new ResponseEntity<Collection<User>>(users,HttpStatus.OK);
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
		User u = ds.getUser(uid);
		//Ensures, that that user has the hatoeas links
		u.hatoeas();
		return new ResponseEntity<User>(u,HttpStatus.OK);
	}
	
    /**
	 * Adds the given user.
	 *
	 * @param t
	 *            the t
	 * @return the response entity< user>
	 */
    @RequestMapping(method = RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> addUser(@RequestBody User u) {
		u = ds.addUser(u);
		//Ensures, that that user has the hatoeas links
		u.hatoeas();
		return new ResponseEntity<User>(u,HttpStatus.CREATED);
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
    	ds.removeUser(uid);
    	return new ResponseEntity<User>(new User(), HttpStatus.NOT_FOUND);
    }
    
    /**
	 * Updates the given user.
	 *
	 * @param t
	 *            the t
	 * @return the response entity< user>
	 */
    @RequestMapping(method = RequestMethod.PUT, consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> updateUser(@RequestBody User u) {
    	//Ensures, that that user has the hatoeas links
    	u.hatoeas();
    	u = ds.updateUser(u);
		return new ResponseEntity<User>(u,HttpStatus.CREATED);
    }
}

