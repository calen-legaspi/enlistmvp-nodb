package com.orangeandbronze.enlistment;

import static org.apache.commons.lang3.Validate.notNull;

import org.apache.commons.lang3.Validate;


class Schedule {
	private final Days days;
	private final Period period;
	
	Schedule(Days days, Period period) {
		Validate.notNull(days);
		Validate.notNull(period);
		this.days = days;
		this.period = period;
	}
	
	void checkOverlap(Schedule other) {
		notNull(other);
		if (this.days.equals(other.days)) {
			this.period.checkOverlap(other.period);
		}
	}
	
	@Override
	public String toString() {
		return days + " " + period;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((days == null) ? 0 : days.hashCode());
		result = prime * result + ((period == null) ? 0 : period.hashCode());
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
		Schedule other = (Schedule) obj;
		if (days != other.days)
			return false;
		if (period != other.period)
			return false;
		return true;
	}
	
	
}

enum Days {
	MTH, TF, WS
}


