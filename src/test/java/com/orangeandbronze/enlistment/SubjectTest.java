package com.orangeandbronze.enlistment;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SubjectTest {

    @Test
    void checkPrereqs_all_prereqs_present() {
        // Given collection that contains all prereqs and a few other subjects
        Subject prereq1 = new Subject("prereq1");
        Subject prereq2 = new Subject("prereq2");
        Subject notPrereq1 = new Subject("notPrereq1");
        Subject notPrereq2 = new Subject("notPrereq2");
        Subject theSubject = new Subject("theSubject", List.of(prereq1, prereq2));
        Collection<Subject> subjectsTaken = List.of(prereq1, prereq2, notPrereq1, notPrereq2);
        // When collection is passed as parameter, Then no exception thrown
        theSubject.checkPrereqs(subjectsTaken);
    }

    @Test
    void checkPrereqs_two_prereqs_missing_from_multiple_prereqs() {
        // Given collection that contains some prereqs but two are missing; and a few other subjects
        Subject prereq1 = new Subject("prereq1");
        Subject prereq2 = new Subject("prereq2");
        Subject prereq3 = new Subject("prereq3");
        Subject prereq4 = new Subject("prereq4");
        Subject notPrereq1 = new Subject("notPrereq1");
        Subject notPrereq2 = new Subject("notPrereq2");
        Subject theSubject = new Subject("theSubject", List.of(prereq1, prereq2, prereq3, prereq4));
        Collection<Subject> subjectsTaken = List.of(prereq1, prereq2, notPrereq1, notPrereq2);
        // When collection is passed as parameter, Then exception is thrown; exception message should show missing prereqs
        Exception e = assertThrows(PrerequisiteException.class, () -> theSubject.checkPrereqs(subjectsTaken));
        assertTrue(e.getMessage().contains("prereq3"));
        assertTrue(e.getMessage().contains("prereq4"));
    }


}
