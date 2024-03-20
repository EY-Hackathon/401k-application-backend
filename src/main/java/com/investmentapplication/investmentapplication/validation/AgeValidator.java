package com.investmentapplication.investmentapplication.validation;
import com.investmentapplication.investmentapplication.validation.AgeGreaterThan;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class AgeValidator implements ConstraintValidator<AgeGreaterThan, Date> {

    @Override
    public boolean isValid(Date dateOfBirth, ConstraintValidatorContext context) {
        if (dateOfBirth == null) {
            return false;
        }
        LocalDate birthDate = new java.sql.Date(dateOfBirth.getTime()).toLocalDate();
        LocalDate now = LocalDate.now();
        return Period.between(birthDate, now).getYears() >= 18;
    }
}
