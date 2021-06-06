package com.orangeandbronze.enlistment;

import static org.apache.commons.lang3.Validate.*;
import static org.apache.commons.lang3.StringUtils.*;

class Room {
	
	private final String name;
	private final int capacity;
	
	Room(String name, int capacity) {
		notBlank(name, "name cannot be blank or null");
		isTrue(isAlphanumeric(name), "sectionId must be alphanumeric, was: " + name);
		isTrue(capacity > 0, "capacity must be greater than 0, was: " + capacity);
		this.name = name;
		this.capacity = capacity;
	}
	
	void checkCapacity(int occupancy) {
		if (occupancy >= capacity) {
			throw new SectionCapacityException("occupancy of " + occupancy + " is at or exceeds capacity of " + capacity);
		}
	}
	
	public String getName() {
		return name;
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Room other = (Room) obj;
		if (capacity != other.capacity)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
