package ru.lanit.constraint;

import ru.lanit.validator.StringEnumValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StringEnumValidator.class)
public @interface StringEnumConstraint {

    String message() default "Не подходящее значение";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends Enum> enumeration();
}
