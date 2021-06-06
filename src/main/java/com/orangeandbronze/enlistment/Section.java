package com.orangeandbronze.enlistment;

import static org.apache.commons.lang3.StringUtils.isAlphanumeric;
import static org.apache.commons.lang3.Validate.*;

import java.util.Collection;
import java.util.concurrent.locks.*;

import com.orangeandbronze.enlistment.DATA.PERIODS;

import static com.orangeandbronze.enlistment.DATA.*;

public class Section {

	private final String sectionId;
	private final Subject subject;
	private final Schedule schedule;
	private final Room room;
	private int numStudentsEnlisted = 0;
	private final ReentrantLock lock = new ReentrantLock();
	
	/** factory method to assemble a new section using the hard-coded dummy data */
	public static Section of(String sectionId, String subjectId, String days, String period, String roomName) {
		var subject = SUBJECTS.stream().filter(subj -> subj.getSubjectId().equals(subjectId)).findFirst().get();
		var room = ROOMS.stream().filter(r -> r.getName().equals(roomName)).findFirst().get();
		var schedule = new Schedule(Days.valueOf(days), PERIODS.valueOf(period).period);
		return new Section(sectionId, subject, schedule, room);
	}

	Section(String sectionId, Subject subject, Schedule schedule, Room room) {
		notBlank(sectionId);
		notNull(subject);
		notNull(schedule);
		notNull(room);
		isTrue(isAlphanumeric(sectionId), "sectionId must be alphanumeric, was: " + sectionId);
		this.sectionId = sectionId;
		this.subject = subject;
		this.schedule = schedule;
		this.room = room;
	}

	void checkConflict(Section other) {
		this.schedule.checkOverlap(other.schedule);
		if (this.subject.equals(other.subject)) {
			throw new SameSubjectException(
					"this section " + this + " and other section " + other + " have same subject " + subject);
		}
	}

	/** Check if prereqs complete. **/
	void checkPrereq(Collection<Subject> subjectsTaken) {
		notNull(subjectsTaken);
		subject.checkPrereqs(subjectsTaken);
	}
	
	/** Increases the number of students enlisted by 1 **/
	void incrementNumStudentsEnlisted() {
		room.checkCapacity(numStudentsEnlisted);
		numStudentsEnlisted++;
	}
	
	void decrementNumStudentsEnlisted() {
		numStudentsEnlisted--;
	}

	/** Locks this object's ReentrantLock **/
	void lock() {
		lock.lock();
	}

	/** Unlock this object's ReentrantLock **/
	void unlock() {
		lock.unlock();
	}
	
	public int getNumStudentsEnlisted() {
		return numStudentsEnlisted;
	}
	
	public String getSectionId() {
		return sectionId;
	}

	public Subject getSubject() {
		return subject;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public Room getRoom() {
		return room;
	}

	@Override
	public String toString() {
		return sectionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sectionId == null) ? 0 : sectionId.hashCode());
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
		Section other = (Section) obj;
		if (sectionId == null) {
			if (other.sectionId != null)
				return false;
		} else if (!sectionId.equals(other.sectionId))
			return false;
		return true;
	}

}
