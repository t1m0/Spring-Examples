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

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The rest end point.
 */
@RestController
@RequestMapping(value = "/rest/", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestEndpoint {

  /**
   * Access user resource
   *
   * @return some string
   */
  @ResponseStatus(value = HttpStatus.OK)
  @RequestMapping(value = "userAccess", method = RequestMethod.GET)
  public ResponseEntity<String> userAccess() {
    return new ResponseEntity<String>("Succesfull accessed user endpoint", HttpStatus.OK);
  }
  
  /**
   * Access admin resource
   *
   * @return some string
   */
  @ResponseStatus(value = HttpStatus.OK)
  @RequestMapping(value = "adminAccess", method = RequestMethod.GET)
  public ResponseEntity<String> adminAccess() {
    return new ResponseEntity<String>("Succesfull accessed admin endpoint", HttpStatus.OK);
  }

}
