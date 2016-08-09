package com.allure.service.impl;

import com.allure.domain.model.Account;
import com.allure.domain.model.Role;
import com.allure.domain.repository.AccountRepository;
import com.allure.domain.repository.RoleRepository;
import com.allure.exception.BusinessException;
import com.allure.http.request.account.LoginRequest;
import com.allure.http.request.account.RegisterRequest;
import com.allure.http.request.account.UpdateRequest;
import com.allure.service.AccountService;
import com.allure.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Created by Administrator on 8/2/2016.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void register(RegisterRequest request) {
        Account account = new Account();
        account.setEmail(request.getEmail());
        account.setNickName(request.getNickName());
        account.setPassword(StringUtils.toMD5(request.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByRoleEquals("ROLE_USER"));
        account.setRoles(roles);
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void update(long accountId, UpdateRequest updateRequest) {
        Account account = accountRepository.findOne(accountId);
        if (!updateRequest.getEmail().equalsIgnoreCase(account.getEmail()) &&
                accountRepository.countByEmailIgnoreCase(updateRequest.getEmail()) > 0) {
            throw new BusinessException("email already exists");
        }
        account.setEmail(updateRequest.getEmail());
        account.setPassword(StringUtils.toMD5(updateRequest.getPassword()));
        account.setNickName(updateRequest.getNickName());
        accountRepository.save(account);
    }

    @Override
    public Account login(LoginRequest request) {
        return accountRepository.findByEmailIgnoreCaseAndPassword(request.getEmail(), StringUtils.toMD5(request.getPassword()));
    }

    @Override
    public Account findById(long id) {
        return accountRepository.findOne(id);
    }
}
