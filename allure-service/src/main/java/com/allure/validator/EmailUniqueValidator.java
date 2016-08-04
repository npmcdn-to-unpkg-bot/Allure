package com.allure.validator;

import com.allure.domain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by yang_shoulai on 2016/8/2.
 */
public class EmailUniqueValidator implements ConstraintValidator<EmailUnique, String> {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void initialize(EmailUnique emailUnique) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {

        return email == null || accountRepository.countByEmailIgnoreCase(email) == 0;
    }
}
