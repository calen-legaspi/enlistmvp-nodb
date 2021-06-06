package com.orangeandbronze.enlistment;

import static com.orangeandbronze.enlistment.Days.*;

import java.time.LocalTime;

public class TestUtils {
	
	public static final Schedule MTH830to10 = new Schedule(MTH, new Period(LocalTime.of(8, 30), LocalTime.of(10, 0)));
	public static final Schedule TF10to1130 = new Schedule(TF, new Period(LocalTime.of(10, 0), LocalTime.of(11, 30)));
	public static final Subject DEFAULT_SUBJECT = new Subject("DefaultSubject");
	public static final String DEFAULT_SECTION_ID = "DefaultSection";
	
	/** Return Student with studentNumber "1", no enlisted sections, no taken subjects **/
	public static Student newDefaultStudent() {
		return new Student(1, "x", "x");
	}
	
	public static Section newDefaultSection() {
		return new Section(DEFAULT_SECTION_ID, DEFAULT_SUBJECT, MTH830to10, new Room("X", 10));
	}
}
