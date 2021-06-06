package com.orangeandbronze.enlistment;

import static com.orangeandbronze.enlistment.Days.MTH;
import static com.orangeandbronze.enlistment.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.Test;

class StudentTest {

	@Test
	void enlist_2_sections_no_conflict() {
		// Given one student and 2 sections no overlapping skeds
		Student student = newDefaultStudent();
		Section sec1 = new Section("A", new Subject("English"), MTH830to10, new Room("X", 10));
		Section sec2 = new Section("B", new Subject("Math"), TF10to1130, new Room("Y", 10));

		// When the student enlists in both sections
		student.enlist(sec1);
		student.enlist(sec2);

		// Then both sections can be found in the student, but no other
		Collection<Section> sections = student.getSections();
		assertAll(() -> assertTrue(sections.containsAll(List.of(sec1, sec2))), () -> assertEquals(2, sections.size()));
	}

	@Test
	void enlist_2_sections_same_schedule() {
		// Given one student and 2 sections w/ same sked
		Student student = newDefaultStudent();
		Section sec1 = new Section("A", DEFAULT_SUBJECT, MTH830to10, new Room("X", 10));
		Section sec2 = new Section("B", DEFAULT_SUBJECT, MTH830to10, new Room("Y", 10));
		// When the student enlists in both sections
		student.enlist(sec1);
		// Then an exception should be thrown in the second enlistment
		assertThrows(ScheduleConflictException.class, () -> student.enlist(sec2));
	}

	@Test
	void enlist_2_sections_overlapping_schedule() {
		// Given one student and 2 sections w/ same sked
		Student student = newDefaultStudent();
		Section sec1 = new Section("A", DEFAULT_SUBJECT, MTH830to10, new Room("X", 10));
		Section sec2 = new Section("B", DEFAULT_SUBJECT,
				new Schedule(MTH, new Period(LocalTime.of(9, 0), LocalTime.of(11, 0))), new Room("Y", 10));
		// When the student enlists in both sections
		student.enlist(sec1);
		// Then an exception should be thrown in the second enlistment
		assertThrows(ScheduleConflictException.class, () -> student.enlist(sec2));
	}

	@Test
	void enlist_in_section_to_overcapacity() {
		// Given a section with capacity 1, and two students
		Section section = new Section("A", DEFAULT_SUBJECT, MTH830to10, new Room("X", 1));
		Student student1 = new Student(1, "x", "x");
		Student student2 = new Student(2, "x", "x");
		// When both students enlist
		student1.enlist(section);
		// Then an exception should be thrown in the second enlistment
		assertThrows(SectionCapacityException.class, () -> student2.enlist(section));
	}

	@Test
	void enlist_concurrent_almost_full_section() throws Exception {
		for (int i = 0; i < 20; i++) { // repeat test 20 times
			// Given multiple students wanting to enlist in a section w/ capacity of 1
			Student student1 = new Student(1, "x", "x");
			Student student2 = new Student(2, "x", "x");
			Student student3 = new Student(3, "x", "x");
			Student student4 = new Student(4, "x", "x");
			Student student5 = new Student(5, "x", "x");
			Section section = new Section("X", DEFAULT_SUBJECT, MTH830to10, new Room("Y", 1));
			// When they enlist concurrently
			CountDownLatch latch = new CountDownLatch(1);
			new EnslistmentThread(student1, section, latch).start();
			new EnslistmentThread(student2, section, latch).start();
			new EnslistmentThread(student3, section, latch).start();
			new EnslistmentThread(student4, section, latch).start();
			new EnslistmentThread(student5, section, latch).start();
			latch.countDown();
			Thread.sleep(100);
			// Only one should be able to enlist
			assertEquals(1, section.getNumStudentsEnlisted());
		}
	}

	private static class EnslistmentThread extends Thread {
		private final Student student;
		private final Section section;
		private final CountDownLatch latch;

		public EnslistmentThread(Student student, Section section, CountDownLatch latch) {
			this.student = student;
			this.section = section;
			this.latch = latch;
		}

		@Override
		public void run() {
			try {
				latch.await(); // The thread keeps waiting till it is informed
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			student.enlist(section);
		}
	}

	@Test
	void enlist_w_missing_prerequisite() {
		// Given a student wants to enlist in a section but has missing prerequisites
		Subject subject = new Subject("Math3", List.of(new Subject("Math2"), new Subject("Math1")));
		Student student = new Student(1, "x", "x", List.of(new Subject("Math1"))); // student has only one prereq
		Section section = new Section("X", subject, MTH830to10, new Room("X", 10));

		// When student tries to enlist, Then exception is thrown
		Exception e = assertThrows(PrerequisiteException.class, () -> student.enlist(section));
		assertEquals("missing prereqs: [Math2]", e.getMessage());
		
	}

}
