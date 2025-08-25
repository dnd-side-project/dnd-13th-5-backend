package com.dnd.sub.global.util;

import com.dnd.sub.domain.subscription.entity.PayCycleUnitType;
import com.dnd.sub.domain.subscription.exception.PayCycleErrorCode;
import com.dnd.sub.domain.subscription.exception.PayCycleException;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@UtilityClass
public class PaymentCycleUtil {

    public static LocalDate previousPaymentDay(LocalDate startedAt, PayCycleUnitType cycleUnit) {
        if(startedAt == null) {
            return null;
        }

        LocalDate now = LocalDate.now();

        switch (cycleUnit) {
            case WEEK:
                if (startedAt.isAfter(now.minusWeeks(1))) {
                    return startedAt;
                }

                final long weeks = ChronoUnit.WEEKS.between(startedAt, now);

                return startedAt.plusWeeks(weeks);

            case MONTH:
                if (startedAt.isAfter(now.minusMonths(1))) {
                    return startedAt;
                }

                final long months = ChronoUnit.MONTHS.between(startedAt, now);

                return addMonths(startedAt.plusMonths(months), startedAt, -1);

            case YEAR:
                if (startedAt.isAfter(now.minusYears(1))) {
                    return startedAt;
                }

                final long years = ChronoUnit.YEARS.between(startedAt, now);

                return addYears(startedAt.plusYears(years), startedAt, -1);

            default:
                throw new PayCycleException(PayCycleErrorCode.CYCLE_TYPE_ERROR);
        }
    }

    public static LocalDate nextPaymentDay(LocalDate previousPaymentDay, LocalDate startedAt, PayCycleUnitType cycleUnit) {
        if(startedAt == null) {
            return null;
        }

        switch (cycleUnit) {
            case WEEK:
                return previousPaymentDay.plusWeeks(1);
            case MONTH:
                return addMonths(previousPaymentDay, startedAt, 1);
            case YEAR:
                return addYears(previousPaymentDay, startedAt, 1);
            default:
                throw new PayCycleException(PayCycleErrorCode.CYCLE_TYPE_ERROR);
        }
    }

    //말일 조정 떄문에 plusMonths 못씀 말일 조정하는 함수
    private static LocalDate addMonths(LocalDate previousPaymentDay, LocalDate startedAt, int months) {
        int startDayOfMonth = startedAt.getDayOfMonth();
        LocalDate nextPaymentDay = previousPaymentDay.plusMonths(months);
        return nextPaymentDay.withDayOfMonth(Math.min(startDayOfMonth, nextPaymentDay.lengthOfMonth()));
    }

    //윤년 조정
    private static LocalDate addYears(LocalDate previousPaymentDay, LocalDate startedAt, int years) {

        int nextPaymentYear = previousPaymentDay.getYear() + years;
        boolean isFeb29 = (startedAt.getMonthValue() == 2 && startedAt.getDayOfMonth() == 29);

        if (previousPaymentDay.getMonthValue() == 2 && isFeb29) {

            if (previousPaymentDay.isLeapYear()) {
                return LocalDate.of(nextPaymentYear, 2, 29);
            }
            return LocalDate.of(nextPaymentYear, 2, 28);
        }

        return previousPaymentDay.plusYears(years);
    }
}
