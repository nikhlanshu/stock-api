package org.latitude.stockapi.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StockDateTimeValidator implements ConstraintValidator<StockDateTime, String> {

    @Override
    public boolean isValid(String dateTime, ConstraintValidatorContext constraintValidatorContext) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            LocalDateTime.parse(dateTime, formatter);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
