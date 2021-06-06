package com.orangeandbronze.enlistment;

import static com.orangeandbronze.enlistment.DATA.*;

import java.util.*;

public class SectionRepository {

	public Optional<Section> findBy(String sectionId) {
		return findAll().stream().filter(sec -> sec.getSectionId().equals(sectionId)).findFirst();
	}

	public void save(Section section) {
		SECTIONS.add(section);
	}

	public Collection<Section> findAll() {
		return SECTIONS;
	}

}
