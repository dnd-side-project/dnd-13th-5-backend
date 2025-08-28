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
        if (startedAt == null) {
            return null;
        }

        LocalDate now = LocalDate.now();
        if (startedAt.isAfter(now)) {
            return null;
        }

        if (startedAt.equals(now)) {
            return startedAt;
        }
        return calculatePreviousPaymentDay(startedAt, now, cycleUnit);
    }

    public static LocalDate nextPaymentDay(LocalDate previousPaymentDay, LocalDate startedAt, PayCycleUnitType cycleUnit) {
        if(startedAt == null) {
            return null;
        }

        LocalDate now = LocalDate.now();

        if(startedAt.isAfter(now)) {
            return startedAt;
        }

        if (previousPaymentDay == null) {
            return calculateNextPaymentDay(startedAt, cycleUnit);
        }

        return calculateNextPaymentDay(previousPaymentDay, cycleUnit);
    }

    private static LocalDate calculatePreviousPaymentDay(LocalDate startedAt, LocalDate now, PayCycleUnitType cycleUnit) {
        LocalDate day = startedAt;

        switch (cycleUnit) {
            case WEEK:
                while (day.plusWeeks(1).isBefore(now) || day.plusWeeks(1).equals(now)) {
                    day = day.plusWeeks(1);
                }
                break;

            case MONTH:
                while (true) {
                    LocalDate nextMonth = addMonths(day, startedAt, 1);
                    if (nextMonth.isAfter(now)) {
                        break;
                    }
                    day = nextMonth;
                }
                break;

            case YEAR:
                while (true) {
                    LocalDate nextYear = addYears(day, startedAt, 1);
                    if (nextYear.isAfter(now)) {
                        break;
                    }
                    day = nextYear;
                }
                break;

            default:
                throw new PayCycleException(PayCycleErrorCode.CYCLE_TYPE_ERROR);
        }

        return day;
    }


    private static LocalDate calculateNextPaymentDay(LocalDate date, PayCycleUnitType cycleUnit) {
        switch (cycleUnit) {
            case WEEK:
                return date.plusWeeks(1);
            case MONTH:
                return addMonths(date, date, 1);
            case YEAR:
                return addYears(date, date, 1);
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
