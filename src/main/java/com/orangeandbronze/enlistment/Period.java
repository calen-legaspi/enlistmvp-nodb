package com.orangeandbronze.enlistment;

import java.time.LocalTime;


import static org.apache.commons.lang3.Validate.*;

class Period {
	
	private final LocalTime start;
	private final LocalTime end;
	
	Period(LocalTime start, LocalTime end) {
		notNull(start);
		notNull(end);
		isTrue(start.isBefore(end), "start cannot be at or after end, start: " + start + " end: " + end);
		checkMinutes(start);
		checkMinutes(end);
		isTrue(!start.isBefore(LocalTime.of(8, 30)), "start time cannot be before 8:30am, was " + start);
		isTrue(!end.isAfter(LocalTime.of(17, 30)), "end time cannot be after 5:30pm, was " + end);
		this.start = start;
		this.end = end;
	}
	
	private static void checkMinutes(LocalTime time) {
		notNull(time);
		isTrue(time.getMinute() == 0 || time.getMinute() == 30, "time must end at :00 or :30, was " + time);
	}
	
	boolean hasOverlap(Period other) {
		notNull(other);
		return this.start.isBefore(other.end) && this.end.isAfter(other.start);
	}

	LocalTime getStart() {
		return start;
	}

	LocalTime getEnd() {
		return end;
	}
	
	@Override
	public String toString() {
		return start + " - " + end;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
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
		Period other = (Period) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}
	
	

}
