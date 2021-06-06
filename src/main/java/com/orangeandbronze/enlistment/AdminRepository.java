package com.orangeandbronze.enlistment;

import static com.orangeandbronze.enlistment.DATA.*;
import java.util.*;

public class AdminRepository {

	public Collection<Admin> findAll() {
		return List.of(RICHARD, MIRANDA, OWEN);
	}

	public Admin findBy(int id) {
		return findAll().stream().filter(admin -> admin.getId() == id).findFirst().orElse(RICHARD);
	}

}
