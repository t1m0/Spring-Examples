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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.t1m0.spring.SpringMVC.exceptions.CustomException;

/**
 * The WebController, which handles the requests to our application.
 */
@Controller
public class WebController {
	
	/**
	 * Adds the given name to the model.
	 *
	 * @param name
	 *            the name
	 * @param model
	 *            the model
	 * @return the resource
	 */
	@RequestMapping("/hello")
	public String hello(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "sayHello";
	}
	
	/**
	 * Throws an exception to test the exception handeling.
	 *
	 * @param type
	 *            the type
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping("/exception/{type}")
	public String exception(@PathVariable(value="type") String type, Model model) {
		if("custom".equals(type)){
			throw new CustomException("My custom exception");
		}else{
			throw new NullPointerException();
		}
	}
	
	/**
	 * Returns the 404 error page.
	 *
	 * @return the resource 
	 */
	@RequestMapping(value="/errors/404")
    public String handle404() {
    	return "error/404";
    }
	
	/**
	 * Handle custom exception.
	 *
	 * @param ex
	 *            the exception
	 * @return the model and view
	 */
	@ExceptionHandler(CustomException.class)
	public ModelAndView handleCustomException(CustomException ex) {
		ModelAndView model = new ModelAndView("error/custom");
		model.addObject("exception", ex);
		return model;
	}
 
	/**
	 * Handle all exceptions.
	 *
	 * @param ex
	 *            the exception
	 * @return the model and view
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		ModelAndView model = new ModelAndView("error/generic");
		model.addObject("exception", ex);
		return model;
	}
}
