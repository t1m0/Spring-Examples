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
package com.t1m0.test.SpringSecurity.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The Class WebController.
 */
@Controller
public class WebController {
	
	/**
	 * Index.
	 *
	 * @return the string
	 */
	@RequestMapping(value = { "/", "/index**" }, method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	/**
	 * Login.
	 *
	 * @return the string
	 */
	@RequestMapping(value="/login**", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
 
	/**
	 * Admin page.
	 *
	 * @return the string
	 */
	@RequestMapping(value = "/secure**", method = RequestMethod.GET)
	public String adminPage() {
		return "secure/secure";
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
	 * Returns the 403 error page.
	 *
	 * @return the resource 
	 */
	@RequestMapping(value="/errors/403")
    public String handle403() {
    	return "error/403";
    }
	
	/**
	 * Returns the 403 error page.
	 *
	 * @return the resource 
	 */
	@RequestMapping(value="/errors/500")
    public String handle500() {
    	return "error/500";
    }
	
	/**
	 * Handle all exceptions.
	 *
	 * @param ex
	 *            the exception
	 * @return the model and view
	 */
	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
		return "error/500";
	}
}
