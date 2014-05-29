package com.t1m0.spring.SpringRestOauth.entities;


@SuppressWarnings("serial")
public class User extends AEntity {

	private String name = null;

	public User() {
		super();
	}

	public User(String name) {
		super();
		this.name = name;
	}

	public User(long uid, String name) {
		super();
		this.name = name;
		setUID(uid);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((name == null) ? 0 : name.hashCode());
		return result;
	}

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
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}

}
