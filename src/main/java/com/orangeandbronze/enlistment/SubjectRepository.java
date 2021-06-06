package com.orangeandbronze.enlistment;

import static com.orangeandbronze.enlistment.DATA.*;

import java.util.*;

public class SubjectRepository {
	
	public Collection<Subject> findAll() {
		return List.of(PE1, PSYCH101, STAT1, MATH1, MATH2, BA101, ECON101, ES1, COM1);
	}

}
