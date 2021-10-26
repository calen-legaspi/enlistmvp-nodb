package com.orangeandbronze.enlistment;

import static com.orangeandbronze.enlistment.Days.*;

import java.time.LocalTime;
import java.util.*;

/**
 * Hardcoded data for MVP or testing purposes. Should be moved to "test"
 * directory in a more mature code base, but I'm leaving this here to make it
 * easier for college students to understand.
 */
class DATA {

	/* SUBJECTS */
	final static Subject PE1 = new Subject("PE1");
	final static Subject PSYCH101 = new Subject("Psych101");
	final static Subject ECON101 = new Subject("Econ101");
	final static Subject BA101 = new Subject("BA101");
	final static Subject ES1 = new Subject("ES1");
	final static Subject COM1 = new Subject("Com1");
	final static Subject MATH1 = new Subject("Math1");
	final static Subject MATH2 = new Subject("Math2", List.of(MATH1));
	final static Subject STAT1 = new Subject("Stat1", List.of(MATH2));
	final static Collection<Subject> SUBJECTS = List.of(PE1, PSYCH101, ECON101, BA101, ES1, COM1, MATH1, MATH2, STAT1);

	/* ROOMS */
	final static Room AS204 = new Room("AS204", 10);
	final static Room AS105 = new Room("AS105", 10);
	final static Room FC103 = new Room("FC103", 10);
	final static Room ENG302 = new Room("ENG302", 10);
	final static Room STAT213 = new Room("Stat213", 10);
	final static Room BA313 = new Room("BA313", 10);
	final static Room ECON123 = new Room("Econ123", 10);
	final static Room PSYCH217 = new Room("Psych217", 10);
	final static Room GYM = new Room("Gym", 10);
	final static Room SMALL_ROOM = new Room("SmallRoom", 1);
	final static Collection<Room> ROOMS = List.of(AS204, AS105, FC103, ENG302, STAT213, BA313, ECON123, PSYCH217, GYM,
			SMALL_ROOM);

	/* SECTIONS */
	final static Section ABC = new Section("ABC", MATH1,
			new Schedule(MTH, new Period(LocalTime.of(8, 30), LocalTime.of(10, 0))), AS204);
	final static Section DEF = new Section("DEF", MATH2,
			new Schedule(TF, new Period(LocalTime.of(10, 0), LocalTime.of(11, 30))), AS105);
	final static Section GHI = new Section("GHI", COM1,
			new Schedule(WS, new Period(LocalTime.of(11, 30), LocalTime.of(13, 00))), FC103);
	final static Section JKL = new Section("JKL", ES1,
			new Schedule(MTH, new Period(LocalTime.of(13, 00), LocalTime.of(14, 30))), ENG302);
	final static Section MNO = new Section("MNO", STAT1,
			new Schedule(TF, new Period(LocalTime.of(14, 30), LocalTime.of(16, 00))), STAT213);
	final static Section PQR = new Section("PQR", BA101,
			new Schedule(WS, new Period(LocalTime.of(16, 00), LocalTime.of(17, 30))), BA313);
	final static Section STU = new Section("STU", ECON101,
			new Schedule(MTH, new Period(LocalTime.of(8, 30), LocalTime.of(10, 0))), ECON123);
	final static Section VWX = new Section("VWX", PSYCH101,
			new Schedule(TF, new Period(LocalTime.of(10, 0), LocalTime.of(11, 30))), PSYCH217);
	final static Section YZA = new Section("YZA", PE1,
			new Schedule(WS, new Period(LocalTime.of(11, 30), LocalTime.of(13, 00))), GYM);
	final static Section SMALL_SECTION = new Section("SmallSection", PE1,
			new Schedule(TF, new Period(LocalTime.of(16, 00), LocalTime.of(17, 30))), SMALL_ROOM);
	final static Collection<Section> SECTIONS = new HashSet<>(List.of(ABC, DEF, GHI, JKL, MNO, PQR, STU, VWX, YZA));

	enum PERIODS {
		P0830(new Period(LocalTime.of(8, 30), LocalTime.of(10, 0))),
		P1000(new Period(LocalTime.of(10, 0), LocalTime.of(11, 30))),
		P1130(new Period(LocalTime.of(11, 30), LocalTime.of(13, 0))),
		P1300(new Period(LocalTime.of(13, 0), LocalTime.of(14, 30))),
		P1430(new Period(LocalTime.of(14, 30), LocalTime.of(16, 0))),
		P1600(new Period(LocalTime.of(16, 0), LocalTime.of(17, 30)));

		final Period period;

		PERIODS(Period period) {
			this.period = period;
		}
	}

	

	/* STUDENTS */
	final static Student MER = new Student(1, "Meredith", "Grey");
	final static Student ALEX = new Student(2, "Alex", "Karev");
	final static Student DEREK = new Student(3, "Derek", "Shepherd");

	/* ADMINS */
	final static Admin RICHARD = new Admin(1, "Richard", "Webber");
	final static Admin MIRANDA = new Admin(2, "Miranda", "Bailey");
	final static Admin OWEN = new Admin(3, "Owen", "Hunt");

}
