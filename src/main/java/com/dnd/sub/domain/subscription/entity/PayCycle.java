package com.dnd.sub.domain.subscription.entity;

import lombok.Getter;

@Getter
public class PayCycle {
    private final int interval;
    private final PayCycleUnitType unit;

    public static PayCycle of(int interval, PayCycleUnitType unit) {
        return new PayCycle(interval, unit);
    }

    private PayCycle(int interval, PayCycleUnitType unit) {
        this.interval = interval;
        this.unit = unit;
    }
}
