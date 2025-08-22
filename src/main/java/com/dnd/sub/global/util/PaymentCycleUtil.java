package com.dnd.sub.global.util;

import com.dnd.sub.domain.subscription.entity.PayCycleUnitType;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class PaymentCycleUtil {

    //다음 결제일
    public static LocalDate nextPaymentDay(LocalDate date, int cycleNum, PayCycleUnitType cycleUnit) {
        switch (cycleUnit) {
            case DAY: return date.plusDays(cycleNum);
            case WEEK: return date.plusWeeks(cycleNum);
            case MONTH: return date.plusMonths(cycleNum);
            case YEAR: return date.plusYears(cycleNum);
            default: return date.plusMonths(cycleNum);
        }
    }
}
