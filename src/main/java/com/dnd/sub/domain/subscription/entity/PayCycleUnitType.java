package com.dnd.sub.domain.subscription.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PayCycleUnitType {
    DAY,
    WEEK,
    MONTH,
    YEAR
    ;
}
