package com.dnd.sub.domain.subscription.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.temporal.ChronoUnit;

@Getter
@RequiredArgsConstructor
public enum PayCycleUnitType {
    WEEK(ChronoUnit.WEEKS),
    MONTH(ChronoUnit.MONTHS),
    YEAR(ChronoUnit.YEARS)
    ;

    private final ChronoUnit chronoUnit;

    public ChronoUnit getChronoUnit() {
        return chronoUnit;
    }
}
