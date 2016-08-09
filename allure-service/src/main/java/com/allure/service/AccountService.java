package com.allure.service;

import com.allure.domain.model.Account;
import com.allure.http.request.account.LoginRequest;
import com.allure.http.request.account.RegisterRequest;
import com.allure.http.request.account.UpdateRequest;

/**
 * Created by Administrator on 8/2/2016.
 */
public interface AccountService {

    void register(RegisterRequest request);

    void update(long accountId, UpdateRequest updateRequest);

    Account login(LoginRequest request);

    Account findById(long id);
}
