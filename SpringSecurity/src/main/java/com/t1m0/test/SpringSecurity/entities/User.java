package com.t1m0.test.SpringSecurity.entities;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails{

	private static final long serialVersionUID = 5673084564754565830L;
	private String userename = null;
	private String password = null;
	private boolean eanbled = true;
	private boolean nonlocked = true;
	
	public User(String userename, String password) {
		super();
		this.userename = userename;
		this.password = password;
	}

	public String getUserename() {
		return userename;
	}

	public void setUserename(String userename) {
		this.userename = userename;
	}

	public boolean isEanbled() {
		return eanbled;
	}

	public void setEanbled(boolean eanbled) {
		this.eanbled = eanbled;
	}

	public boolean isNonlocked() {
		return nonlocked;
	}

	public void setNonlocked(boolean nonlocked) {
		this.nonlocked = nonlocked;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	@SuppressWarnings("serial")
	public Collection<? extends GrantedAuthority> getAuthorities() {
		GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_USER");
		return new ArrayList<GrantedAuthority>(){{add(auth);}};
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userename;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return nonlocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return eanbled;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (eanbled ? 1231 : 1237);
		result = prime * result + (nonlocked ? 1231 : 1237);
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((userename == null) ? 0 : userename.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (eanbled != other.eanbled)
			return false;
		if (nonlocked != other.nonlocked)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userename == null) {
			if (other.userename != null)
				return false;
		} else if (!userename.equals(other.userename))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "User [userename=" + userename + ", password=" + password
				+ ", eanbled=" + eanbled + ", nonlocked=" + nonlocked + "]";
	}

}
