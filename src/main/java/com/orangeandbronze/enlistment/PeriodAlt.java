package com.orangeandbronze.enlistment;

import static org.apache.commons.lang3.Validate.*;

class PeriodAlt {

    private final Time start;
    private final Time end;

    PeriodAlt(Time start, Time end) {
        notNull(start);
        notNull(end);
        isTrue(start.isBefore(end), "start cannot be at or after end, start: " + start + " end: " + end);
        this.start = start;
        this.end = end;
    }

    boolean hasOverlap(PeriodAlt other) {
        notNull(other);
        return this.start.isBefore(other.end) && this.end.isAfter(other.start);
    }

    @Override
    public String toString() {
        return start + " - " + end;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((end == null) ? 0 : end.hashCode());
        result = prime * result + ((start == null) ? 0 : start.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PeriodAlt other = (PeriodAlt) obj;
        if (end == null) {
            if (other.end != null)
                return false;
        } else if (!end.equals(other.end))
            return false;
        if (start == null) {
            return other.start == null;
        } else return start.equals(other.start);
    }
}

enum Time {
    H0830, H0900, H0930, H1000, H1030, H1100, H1130, H1200, H1230, H1300,
    H1330, H1400, H1430, H1500, H1530, H1600, H1630, H1700, H1730;

    boolean isBefore(Time other) {
        return this.ordinal() < other.ordinal();
    }

    boolean isAfter(Time other) {
        return this.ordinal() > other.ordinal();
    }
}
