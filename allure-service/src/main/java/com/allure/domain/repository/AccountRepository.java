package com.allure.domain.repository;

import com.allure.domain.BaseRepository;
import com.allure.domain.model.Account;

import java.util.List;

/**
 * Created by Administrator on 8/2/2016.
 */

public interface AccountRepository extends BaseRepository<Account> {

    Long countByEmailIgnoreCase(String email);

    Account findByEmailIgnoreCaseAndPassword(String email, String password);

    Account findByEmailIgnoreCase(String email);
}
