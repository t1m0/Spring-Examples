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
package com.t1m0.spring.SpringHATOEAS.entities;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

import com.t1m0.spring.SpringHATOEAS.endpoints.TodoEndpoint;

/**
 * The abstract entity AEntity which encapsulates the id for all entities.
 * To support HATOES {@link ResourceSupport} is extended.
 */
public abstract class AEntity extends ResourceSupport implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6218733503108385870L;
	
	/** The uid. */
	private Long UID = null;

	/**
	 * Abstrac method to generate the HATOEAS links for the entity, which implements the method.
	 */
	abstract public void hatoeas();
	
	/**
	 * Gets the uid.
	 *
	 * @return the uid
	 */
	public Long getUID() {
		return UID;
	}

	/**
	 * Sets the uid.
	 *
	 * @param uID
	 *            the uid
	 */
	public void setUID(Long uID) {
		UID = uID;
	}
	
	
}
