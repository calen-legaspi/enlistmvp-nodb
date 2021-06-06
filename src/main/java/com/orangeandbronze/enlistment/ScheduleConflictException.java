package com.orangeandbronze.enlistment;


class ScheduleConflictException extends EnlistmentException {


	public ScheduleConflictException(String message) {
		super(message);
	}

	public ScheduleConflictException(Schedule thisSched, Schedule otherSched) {
		super("This: " + thisSched + " Other: " + otherSched);
	}
	

}
