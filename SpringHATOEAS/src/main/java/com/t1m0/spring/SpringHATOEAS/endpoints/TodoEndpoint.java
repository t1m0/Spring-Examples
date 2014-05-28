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

import javassist.tools.framedump;

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

import com.t1m0.spring.SpringHATOEAS.entities.Todo;
import com.t1m0.spring.SpringHATOEAS.services.IDataService;

/**
 * The rest end point for the resource {@link Todo}.
 */
@RestController
@RequestMapping(value="/todo", produces=MediaType.APPLICATION_JSON_VALUE)
public class TodoEndpoint {
	
	@Autowired
	IDataService ds;
	
	/**
	 * Gets a list of all todos.
	 *
	 * @return the list of todos
	 */
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Collection<Todo>> getListTodo() {
		Collection<Todo> todos = ds.allTodos();
		//Ensures, that each todo has the hatoeas links
		todos.forEach(
			t-> t.hatoeas());
		return new ResponseEntity<Collection<Todo>>(todos,HttpStatus.OK);
	}
	
	/**
	 * Gets the requested todo.
	 *
	 * @param uid
	 *            the uid
	 * @return the todo
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{uid}")
	public ResponseEntity<Todo> getTodo(@PathVariable long uid) {
		Todo t = ds.getTodo(uid);
		//Ensures, that that todo has the hatoeas links
		t.hatoeas();
		return new ResponseEntity<Todo>(t,HttpStatus.OK);
	}
	
    /**
	 * Adds the given todo.
	 *
	 * @param t
	 *            the t
	 * @return the response entity< todo>
	 */
    @RequestMapping(method = RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Todo> addTodo(@RequestBody Todo t) {
		t = ds.addTodo(t);
		//Ensures, that that todo has the hatoeas links
		t.hatoeas();
		return new ResponseEntity<Todo>(t,HttpStatus.CREATED);
    }
    
    /**
	 * Deletes the given todo.
	 *
	 * @param uid
	 *            the uid
	 * @return the response entity< todo>
	 */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{uid}")
    ResponseEntity<Todo> deleteTodo(@PathVariable long uid) {
    	ds.removeTodo(uid);
    	return new ResponseEntity<Todo>(new Todo(), HttpStatus.NOT_FOUND);
    }
    
    /**
	 * Updates the given todo.
	 *
	 * @param t
	 *            the t
	 * @return the response entity< todo>
	 */
    @RequestMapping(method = RequestMethod.PUT, consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo t) {
    	//Ensures, that that todo has the hatoeas links
    	t.hatoeas();
    	t = ds.updateTodo(t);
		return new ResponseEntity<Todo>(t,HttpStatus.CREATED);
    }
}
