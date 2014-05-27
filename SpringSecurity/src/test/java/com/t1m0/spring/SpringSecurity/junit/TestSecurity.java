package com.t1m0.spring.SpringSecurity.junit;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.t1m0.test.SpringSecurity.entities.User;

public class TestSecurity {

	@SuppressWarnings("serial")
	private final Set<User> users = new HashSet<User>(){{
		add(new User("timo","test"));
		add(new User("hans","test"));
		add(new User("fritz","test"));
	}};
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
	}

}
