package com.allure.http.request.account;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by yang_shoulai on 2016/8/2.
 */
public class LoginRequest {

    @NotNull(message = "{account.login.email.empty}")
    @NotBlank(message = "{account.login.email.empty}")
    private String email;

    @NotNull(message = "{account.login.password.empty}")
    @NotBlank(message = "{account.login.password.empty}")
    private String password;

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
}
