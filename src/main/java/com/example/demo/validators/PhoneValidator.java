package com.example.demo.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<PhoneFormat, String> {
@Override
public boolean isValid(String value, ConstraintValidatorContext context) {
	return value != null
			&& value.matches("[0-9]+")
			&& value.length() >= 8
			&& value.length() <= 14;
}
}
