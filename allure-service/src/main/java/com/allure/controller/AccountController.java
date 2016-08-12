package com.allure.controller;

import com.allure.common.utils.VerifyCodeUtils;
import com.allure.domain.model.Account;
import com.allure.domain.model.Role;
import com.allure.http.ApiResponse;
import com.allure.http.request.account.RegisterRequest;
import com.allure.http.request.account.UpdateRequest;
import com.allure.http.response.account.LoginResponse;
import com.allure.service.AccountService;
import com.allure.utils.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        String verifyCode = (String) SessionUtils.getAttribute(request, SessionUtils.KEY_VERIFY_CODE);
        if (!registerRequest.getValidationCode().equalsIgnoreCase(verifyCode)) {
            return new ApiResponse.Builder().exception().globalError(null, "验证码不正确").build();
        }
        accountService.register(registerRequest);
        return new ApiResponse.Builder().success().build();
    }


    @RequestMapping(value = "/reload", method = RequestMethod.GET)
    @PermitAll
    public ApiResponse reload() {
        long accountId = getAccountId();
        if (accountId <= 0) {
            return new ApiResponse.Builder().success().result(null).build();
        }
        Account account = accountService.findById(accountId);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(account.getId());
        loginResponse.setNickName(account.getNickName());
        loginResponse.setEmail(account.getEmail());
        Set<Role> roles = account.getRoles();
        List<String> roleList = new ArrayList<>();
        roles.forEach(role -> roleList.add(role.getRole()));
        loginResponse.setRoles(roleList);
        return new ApiResponse.Builder().success().result(loginResponse).build();
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

    @RequestMapping(value = "/verifyCode", method = RequestMethod.GET)
    @PermitAll
    public ApiResponse verifyCode(HttpServletRequest request) throws IOException {
        String code = VerifyCodeUtils.generateVerifyCode(4);
        String base64 = VerifyCodeUtils.outputVerifyImageAsBase64(200, 80, code);
        SessionUtils.setAttribute(request, SessionUtils.KEY_VERIFY_CODE, code);
        return new ApiResponse.Builder().success().result(base64).build();
    }

}
