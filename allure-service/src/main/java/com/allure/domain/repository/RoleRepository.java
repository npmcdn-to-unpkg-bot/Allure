package com.allure.domain.repository;

import com.allure.domain.BaseRepository;
import com.allure.domain.model.Role;

/**
 * Created by yang_shoulai on 8/3/2016.
 */
public interface RoleRepository extends BaseRepository<Role> {
    Role findByRoleEquals(String role);
}
