package org.latitude.stockapi.validator;

import java.time.LocalDateTime;

public interface StartDateEndDateValidator {

    static void isEndDateTimeIsBeforeStartDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (endDateTime.isBefore(startDateTime)) {
            throw new IllegalArgumentException("end date time can not be before start date time");
        }
    }

}
