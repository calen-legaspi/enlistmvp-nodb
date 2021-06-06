package com.orangeandbronze.enlistment;

import static com.orangeandbronze.enlistment.DATA.*;

import java.util.*;

public class StudentRepository {

	public Student findBy(int studentNumber) {
		return findAll().stream().filter(student -> student.getStudentNumber() == studentNumber).findFirst().orElse(MER);
	}

	public void save(Student student) {
	}

	public Collection<Student> findAll() {
		return List.of(MER, ALEX, DEREK);
	}

}
