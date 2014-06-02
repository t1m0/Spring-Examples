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
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.t1m0.spring.SpringRestOauth.entities.Client;
import com.t1m0.spring.SpringRestOauth.entities.User;
import com.t1m0.spring.SpringRestOauth.services.interfaces.LIClient;

/**
 * The secure rest end point.
 */
@RestController
@RequestMapping(value="/client", produces=MediaType.APPLICATION_JSON_VALUE)
public class ClientEndpoint {

	/** The dao. */
	@Autowired
	private LIClient dao;

	@PostConstruct
	private void inital(){
		@SuppressWarnings("serial")
		Set<String> scope = new HashSet<String>(){{
			add("read");
			add("write");
			add("trust");
		}};
		Set<String> grantTypes = new HashSet<String>(){{
			add("password");
			add("authorization_code");
			add("refresh_token");
			add("implicit");
			add("client_credentials");
		}};
		Client c0 = new Client("my-trusted-client-with-secret", "somesecret");
		c0.setAuthorizedGrantTypes(grantTypes);
		c0.setAuthorities(AuthorityUtils.createAuthorityList(User.Role.ROLE_USER.name()));
		c0.setScoped(false);
		c0.setScope(scope);
		dao.create(c0);
		Client c1 = new Client("web-client");
		c1.setAuthorizedGrantTypes(grantTypes);
		c1.setAuthorities(AuthorityUtils.createAuthorityList(User.Role.ROLE_USER.name()));
		c1.setScoped(false);
		c1.setScope(scope);
		dao.create(c1);
	}

	/**
	 * Gets a list of all clients.
	 *
	 * @return the list of clients
	 */
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Collection<Client>> getListClient() {
		return new ResponseEntity<Collection<Client>>(dao.all(),HttpStatus.OK);
	}

	/**
	 * Gets the requested client.
	 *
	 * @param uid
	 *            the uid
	 * @return the client
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{uid}")
	public ResponseEntity<Client> getClient(@PathVariable long uid) {
		return new ResponseEntity<Client>(dao.read(uid),HttpStatus.OK);
	}

	/**
	 * Adds the given client.
	 *
	 * @param u
	 *            the u
	 * @return the response entity< client>
	 */
	@RequestMapping(method = RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Client> addClient(@RequestBody Client u) {
		return new ResponseEntity<Client>(dao.create(u),HttpStatus.CREATED);
	}

	/**
	 * Deletes the given client.
	 *
	 * @param uid
	 *            the uid
	 * @return the response entity< client>
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{uid}")
	ResponseEntity<Client> deleteClient(@PathVariable long uid) {
		Client u = dao.read(uid);
		dao.delete(u);
		return new ResponseEntity<Client>(u, HttpStatus.NOT_FOUND);
	}
}
