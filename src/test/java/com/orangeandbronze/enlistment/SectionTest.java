package com.orangeandbronze.enlistment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static com.orangeandbronze.enlistment.TestUtils.*;

class SectionTest {

	@Test
	void new_same_sked_same_room() {
		Room room = new Room("X", 10);
		Section sec1 = new Section("A", DEFAULT_SUBJECT, MTH830to10, room);
		assertThrows(ScheduleConflictException.class, () -> new Section("B", DEFAULT_SUBJECT, MTH830to10, room));
	}

}
