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
package com.t1m0.spring.SpringMVC.controller;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * The WebController, which handles the requests to our application.
 */
@Controller
public class WebController {

  /**
   * Index mapping
   *
   * @return the resource
   */
  @RequestMapping("/")
  public ModelAndView index() {
    ModelAndView modelView = new ModelAndView("index");
    return modelView;
  }
  
  /**
   * Adds the given name to the model.
   *
   * @param name
   *          the name
   * @return the resource
   */
  @RequestMapping("/hello")
  public ModelAndView hello(@RequestParam(value = "name", required = false, defaultValue = "World") final String name) {
    ModelAndView modelView = new ModelAndView("helloPage");
    modelView.addObject("name", name);
    return modelView;
  }
  
  /**
   * Throws an exception
   *
   * @return the resource
   * @throws FileNotFoundException
   */
  @RequestMapping("/causeError")
  public ModelAndView causeError() throws FileNotFoundException {
    throw new FileNotFoundException("file not found");
  }
  
  @ExceptionHandler(Throwable.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ModelAndView exceptionHandler(final Exception ex) {
    ModelAndView modelView = new ModelAndView("error/exception");
    modelView.addObject("msg", ex.getMessage());
    return modelView;
  }
  
}
