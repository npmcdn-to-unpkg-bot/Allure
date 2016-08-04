package com.allure.domain.model;

import com.allure.domain.Model;
import com.allure.validator.EmailUnique;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by yang_shoulai on 2016/8/1.
 */
@Entity
@Table(name = "sys_account")
public class Account extends Model {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_account_role",
            joinColumns = {@JoinColumn(name = "account")},
            inverseJoinColumns = {@JoinColumn(name = "role")})
    private Set<Role> roles;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
