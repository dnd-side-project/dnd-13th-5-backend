package com.dnd.sub.global.util;

import com.dnd.sub.domain.subscription.entity.PayCycleUnitType;
import com.dnd.sub.domain.subscription.exception.CycleErrorCode;
import com.dnd.sub.domain.subscription.exception.CycleException;
import lombok.experimental.UtilityClass;

import java.time.DateTimeException;
import java.time.LocalDate;

@UtilityClass
public class PaymentCycleUtil {

    //다음 결제일 date는 현재 다음 결제예정일, startDate는 최초 예정일
    public static LocalDate nextPaymentDay(LocalDate date, LocalDate startDate, int cycleNum, PayCycleUnitType cycleUnit) {
        switch (cycleUnit) {
            case DAY:
                return date.plusDays(cycleNum);
            case WEEK:
                return date.plusWeeks(cycleNum);
            case MONTH:
                return addMonths(date, startDate, cycleNum);
            case YEAR:
                return addYears(date, startDate, cycleNum);
            default:
                throw new CycleException(CycleErrorCode.CYCLE_TYPE_ERROR);
        }
    }

    //말일 조정 떄문에 plusMonths 못씀 말일 조정하는 함수
    private static LocalDate addMonths(LocalDate date, LocalDate startDate, int months) {
        int day = startDate.getDayOfMonth();
        LocalDate nextDate = date.plusMonths(months);
        return nextDate.withDayOfMonth(Math.min(day, nextDate.lengthOfMonth()));
    }

    //윤년 조정
    private static LocalDate addYears(LocalDate date, LocalDate startDate, int years) {

        int nextYear = date.getYear() + years;
        boolean isLeapYear = (nextYear % 4 == 0 && nextYear % 100 != 0) || nextYear % 400 == 0;
        boolean isFeb29 = (startDate.getMonthValue() == 2 && startDate.getDayOfMonth() == 29);

        if (date.getMonthValue() == 2 && isFeb29) {

            if (isLeapYear) {
                return LocalDate.of(nextYear, 2, 29);
            }
            return LocalDate.of(nextYear, 2, 28);
        }

        return date.plusYears(years);
    }
}
