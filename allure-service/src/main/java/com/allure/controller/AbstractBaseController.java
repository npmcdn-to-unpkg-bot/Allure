package com.allure.controller;

import com.allure.config.UserService;
import com.allure.http.ApiResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * Created by yang_shoulai on 2016/8/2.
 */
public class AbstractBaseController {


    protected ApiResponse fromBindingResult(BindingResult bindingResult) {
        ApiResponse.Builder builder = new ApiResponse.Builder().exception();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrorList) {
            builder.filedError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return builder.build();
    }


    protected long getAccountId() {
        return ((UserService.LoggedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }


}
