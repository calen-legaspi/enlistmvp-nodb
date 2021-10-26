package com.orangeandbronze.enlistment;

import static org.apache.commons.lang3.Validate.*;

public class Admin {
	
	private final int id;
	private final String firstname;
	private final String lastname;
	
	Admin(int id, String firstname, String lastname) {
		notBlank(firstname);
		notBlank(lastname);
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	
	
	public int getId() {
		return id;
	}



	public String getFirstname() {
		return firstname;
	}



	public String getLastname() {
		return lastname;
	}



	@Override
	public String toString() {
		return "Admin#" + id + " " + firstname + " " + lastname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Admin other = (Admin) obj;
		return id == other.id;
	}

	
	
	
	

}
