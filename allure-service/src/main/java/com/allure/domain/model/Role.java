package com.allure.domain.model;

import com.allure.domain.Model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Administrator on 8/3/2016.
 */
@Entity
@Table(name = "sys_role")
public class Role extends Model {

    @Column(nullable = false, unique = true)
    private String role;

    @Column(length = 1000)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_account_role",
            joinColumns = {@JoinColumn(name = "role")},
            inverseJoinColumns = {@JoinColumn(name = "account")})
    private Set<Account> accounts;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
