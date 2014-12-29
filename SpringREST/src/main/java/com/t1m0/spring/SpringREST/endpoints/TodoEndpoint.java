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
package com.t1m0.spring.SpringREST.endpoints;

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

import com.t1m0.spring.SpringREST.entities.Todo;

/**
 * The rest end point.
 */
@RestController
@RequestMapping(value = "/todo", produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoEndpoint {

  /** The id_counter. */
  private long                  id_counter = 0;

  /** The todos. */
  @SuppressWarnings("serial")
  private final Map<Long, Todo> todos      = new HashMap<Long, Todo>() {
    {
      put(id_counter, new Todo(id_counter, "Test 0", "Test 0"));
      put(++id_counter, new Todo(id_counter, "Test 1", "Test 1"));
      put(++id_counter, new Todo(id_counter, "Test 2", "Test 2"));
    }
  };

  /**
   * Gets a list of all todos.
   *
   * @return the list of todos
   */
  @ResponseStatus(value = HttpStatus.OK)
  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Collection<Todo>> getListTodo() {
    return new ResponseEntity<Collection<Todo>>(todos.values(), HttpStatus.OK);
  }

  /**
   * Gets the requested todo.
   *
   * @param uid
   *          the uid
   * @return the todo
   */
  @RequestMapping(method = RequestMethod.GET, value = "/{uid}")
  public ResponseEntity<Todo> getTodo(@PathVariable final long uid) {
    return new ResponseEntity<Todo>(todos.get(uid), HttpStatus.OK);
  }

  /**
   * Adds the given todo.
   *
   * @param t
   *          the t
   * @return the response entity< todo>
   */
  @RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<Todo> addTodo(@RequestBody final Todo t) {
    t.setId(++id_counter);
    todos.put(id_counter, t);
    return new ResponseEntity<Todo>(t, HttpStatus.CREATED);
  }

  /**
   * Deletes the given todo.
   *
   * @param uid
   *          the uid
   * @return the response entity< todo>
   */
  @RequestMapping(method = RequestMethod.DELETE, value = "/{uid}")
  ResponseEntity<Todo> deleteTodo(@PathVariable final long uid) {
    return new ResponseEntity<Todo>(todos.remove(uid), HttpStatus.NOT_FOUND);
  }
}
