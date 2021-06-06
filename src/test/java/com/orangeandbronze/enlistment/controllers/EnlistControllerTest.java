package com.orangeandbronze.enlistment.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.*;

import com.orangeandbronze.enlistment.*;
import static com.orangeandbronze.enlistment.TestUtils.*;

class EnlistControllerTest {

	@Test
	void enlist_student_in_section() {
		StudentRepository studentRepo = mock(StudentRepository.class);
		Student student = newDefaultStudent();
		when(studentRepo.findBy(1)).thenReturn(student);

		SectionRepository sectionRepo = mock(SectionRepository.class);
		Section section = newDefaultSection();
		when(sectionRepo.findBy(DEFAULT_SECTION_ID)).thenReturn(Optional.of(section));		

		EnlistController controller = new EnlistController();
		controller.setSectionRepo(sectionRepo);
		controller.setStudentRepo(studentRepo);
		controller.enlist(student, DEFAULT_SECTION_ID, "enlist");
		Collection<Section> sections = student.getSections();
		assertAll(() -> assertTrue(sections.contains(section)), () -> assertEquals(1, sections.size()));
		verify(studentRepo).save(student);
		verify(sectionRepo).save(section);
	}

}
