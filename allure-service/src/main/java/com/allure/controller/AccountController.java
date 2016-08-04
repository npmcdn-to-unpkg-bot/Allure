package com.allure.controller;

import com.allure.http.ApiResponse;
import com.allure.http.request.account.RegisterRequest;
import com.allure.http.request.account.UpdateRequest;
import com.allure.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Administrator on 8/2/2016.
 */
@RestController
@RequestMapping("/accounts")
public class AccountController extends AbstractBaseController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @PermitAll
    public ApiResponse register(@Valid @RequestBody RegisterRequest registerRequest, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return fromBindingResult(bindingResult);
        }
        accountService.register(registerRequest);
        return new ApiResponse.Builder().success().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ApiResponse update(@PathVariable("id") long id, @Valid @RequestBody UpdateRequest updateRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return fromBindingResult(bindingResult);
        }
        accountService.update(id, updateRequest);
        return new ApiResponse.Builder().success().build();
    }

}
