package com.allure.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by yang_shoulai on 2016/8/2.
 */
public class TelephoneValidator implements ConstraintValidator<Telephone, String> {

    private Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

    @Override
    public void initialize(Telephone telephone) {
    }

    @Override
    public boolean isValid(String telephone, ConstraintValidatorContext constraintValidatorContext) {
        return telephone == null || pattern.matcher(telephone).matches();
    }
}
