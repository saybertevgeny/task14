package ru.lanit.validator;

import ru.lanit.constraint.StringEnumConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;

public class StringEnumValidator implements ConstraintValidator<StringEnumConstraint, String> {

    private ArrayList<String> mayValues;

    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        if (str == null) {
            return false;
        }
        return this.mayValues.indexOf(str.toUpperCase()) >= 0;
    }

    @Override
    public void initialize(StringEnumConstraint constraintAnnotation) {
        if (!constraintAnnotation.enumeration().isEnum())
            throw new IllegalArgumentException();
        Enum[] priorityEnums = constraintAnnotation.enumeration().getEnumConstants();
        mayValues = new ArrayList<>();
        for (Enum priorityEnum : priorityEnums) {
            this.mayValues.add(priorityEnum.name());
        }
    }
}
