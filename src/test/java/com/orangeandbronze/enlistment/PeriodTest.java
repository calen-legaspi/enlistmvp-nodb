package com.orangeandbronze.enlistment;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

class PeriodTest {

	private static Stream<Arguments> params() {
		return Stream.of(
				Arguments.of(new Period(LocalTime.of(8, 30), LocalTime.of(10, 0)),
						new Period(LocalTime.of(9, 0), LocalTime.of(11, 0))),
				Arguments.of(new Period(LocalTime.of(8, 30), LocalTime.of(10, 0)),
						new Period(LocalTime.of(9, 0), LocalTime.of(9, 30))),
				Arguments.of(new Period(LocalTime.of(9, 00), LocalTime.of(11, 0)),
						new Period(LocalTime.of(8, 30), LocalTime.of(10, 0))),
				Arguments.of(new Period(LocalTime.of(9, 0), LocalTime.of(9, 30)),
						new Period(LocalTime.of(8, 30), LocalTime.of(10, 0))));
	}

	@ParameterizedTest
	@MethodSource("params")
	void checkOverlap(Period p1, Period p2) {
		assertThrows(ScheduleConflictException.class, () -> p1.checkOverlap(p2));
	}

}
