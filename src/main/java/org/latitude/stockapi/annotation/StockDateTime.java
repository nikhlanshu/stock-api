package org.latitude.stockapi.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = StockDateTimeValidator.class)
public @interface StockDateTime {

    String message() default "DateTime must in yyyy-MM-dd HH:mm format";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
