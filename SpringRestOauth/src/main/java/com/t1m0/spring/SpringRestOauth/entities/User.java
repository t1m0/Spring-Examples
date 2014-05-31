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
package com.t1m0.spring.SpringRestOauth.entities;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * The Class User.
 */
@Entity
@Table(name="tbl_users")
@SuppressWarnings("serial")
public class User extends AEntity implements UserDetails {

	/**
	 * Wrapper to set the {@link GrantedAuthority} for the user.
	 */
	public static enum Role {
		/** The role user. */
		ROLE_USER,
		/** The role admin. */
		ROLE_ADMIN;

		/**
		 * Gets the authority.
		 *
		 * @return the authority
		 */
		public GrantedAuthority getAuthority(){
			GrantedAuthority auth = null;
			switch(this){
			case ROLE_USER:
				auth = new SimpleGrantedAuthority("ROLE_USER");
				break;
			case ROLE_ADMIN:
				auth = new SimpleGrantedAuthority("ROLE_ADMIN");
				break;
			}
			return auth;
		}
	}

	/** The username. */
	@Column(name="username", unique=true, nullable=false)
	private String username = null;

	/** The password. */
	@Column(name="password")
	private String password = null;

	/** Indicates whether the user account is expired. */
	@Column(name="expired")
	private boolean expired = false;

	/** Indicates whether the user account is locked. */
	@Column(name="locked")
	private boolean locked = false;

	/** Indicates whether the user account is expired. */
	@Column(name="enabled")
	private boolean enabled = true;

	/** Indicates whether the user credential (password) is expired. */
	@Column(name="credentialsExpired")
	private boolean credentialsExpired = false;

	/** The role which the user is assigned to. */
	@Column(name="role")
	private Role role = null;

	/**
	 * The Constructor.
	 */
	public User() {
		super();
	}


	/**
	 * The Constructor.
	 *
	 * @param username
	 *            the username
	 */
	public User(String username) {
		super();
		this.username = username;
	}

	/**
	 * The Constructor.
	 *
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @param role
	 *            the role
	 */
	public User(String username, String password, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	/**
	 * Sets the username.
	 *
	 * @param username
	 *            the username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Sets the expired.
	 *
	 * @param expired
	 *            the expired
	 */
	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	/**
	 * Sets the locked.
	 *
	 * @param locked
	 *            the locked
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled
	 *            the enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Sets the credentials expired.
	 *
	 * @param credentialsExpired
	 *            the credentials expired
	 */
	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	/**
	 * Sets the authorities.
	 *
	 * @param authorities
	 *            the authorities
	 */
	public void setAuthorities(List<GrantedAuthority> authorities) {
	}

	/**
	 * Sets the role.
	 *
	 * @param role
	 *            the role
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList(role.name());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAccountNonExpired() {
		return !expired;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + (credentialsExpired ? 1231 : 1237);
		result = (prime * result) + (enabled ? 1231 : 1237);
		result = (prime * result) + (expired ? 1231 : 1237);
		result = (prime * result) + (locked ? 1231 : 1237);
		result = (prime * result)
				+ ((password == null) ? 0 : password.hashCode());
		result = (prime * result) + ((role == null) ? 0 : role.hashCode());
		result = (prime * result)
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (credentialsExpired != other.credentialsExpired) {
			return false;
		}
		if (enabled != other.enabled) {
			return false;
		}
		if (expired != other.expired) {
			return false;
		}
		if (locked != other.locked) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (role != other.role) {
			return false;
		}
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", expired=" + expired + ", locked=" + locked + ", enabled="
				+ enabled + ", credentialsExpired=" + credentialsExpired
				+ ", role=" + role + "]";
	}

}
