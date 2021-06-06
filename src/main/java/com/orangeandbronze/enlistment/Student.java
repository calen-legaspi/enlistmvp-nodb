package com.orangeandbronze.enlistment;

import java.util.*;

import static org.apache.commons.lang3.Validate.*;

public class Student {

	private final int studentNumber;
	private final Set<Section> sections = new HashSet<>();
	private final Set<Subject> subjectsTaken = new HashSet<>();
	private final String firstname;
	private final String lastname;

	Student(int studentNumber, String firstname, String lastname, Collection<Section> sections,
			Collection<Subject> subjectsTaken) {
		isTrue(studentNumber >= 0, "studentNumber should be non-negative, was: " + studentNumber);
		notBlank(firstname);
		notBlank(lastname);
		notNull(sections, "sections was null");
		this.studentNumber = studentNumber;
		this.firstname = firstname;
		this.lastname = lastname;
		this.sections.addAll(sections);
		this.sections.remove(null); // remove any nulls
		this.subjectsTaken.addAll(subjectsTaken);
		this.subjectsTaken.remove(null);
	}

	/** Instantiate student with no sections enlisted and no subjects taken. **/
	public Student(int studentNumber, String firstname, String lastname) {
		this(studentNumber, firstname, lastname, Collections.emptyList(), Collections.emptyList());
	}

	/** Instantiate student with non sections enlisted but with subjects taken. **/
	Student(int studentNumber, String firstname, String lastname, Collection<Subject> subjectsTaken) {
		this(studentNumber, firstname, lastname, Collections.emptyList(), subjectsTaken);
	}

	public void enlist(Section newSection) {
		notNull(newSection);
		sections.forEach(currSection -> {
			if (currSection.hasScheduleConflict(newSection)) {
				throw new ScheduleConflictException("Current section " + currSection + " with schedule "
						+ currSection.getSchedule() + " has schedule conflict with new secton " + newSection
						+ " at schedule " + newSection.getSchedule());
			}
		});
		newSection.checkPrereq(subjectsTaken);
		newSection.lock();
		// make sure only one thread at a time
		try {
			newSection.incrementNumStudentsEnlisted();
			sections.add(newSection);
		} finally {
			newSection.unlock();
		}
	}

	public void cancel(Section section) {
		notNull(section);
		section.lock();
		try {
			if (sections.remove(section)) {
				section.decrementNumStudentsEnlisted();
			}
		} finally {
			section.unlock();
		}
	}

	public int getStudentNumber() {
		return studentNumber;
	}

	public Collection<Subject> getSubjectsTaken() {
		return new ArrayList<>(subjectsTaken);
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public Collection<Section> getSections() {
		return new ArrayList<>(sections);
	}

	@Override
	public String toString() {
		return "Student #" + studentNumber + " " + firstname + " " + lastname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + studentNumber;
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
		Student other = (Student) obj;
		if (studentNumber != other.studentNumber)
			return false;
		return true;
	}

}
