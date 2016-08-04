package com.allure.http.request.account;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by yang_shoulai on 2016/8/2.
 */
public class UpdateRequest {

    @NotNull(message = "{account.register.email.notNull}")
    @NotBlank(message = "{account.register.email.notNull}")
    @Email(message = "{account.register.email}")
    private String email;

    @NotNull(message = "{account.register.nickName.notNull}")
    @Length(min = 1, max = 20, message = "{account.register.nickName.length}")
    private String nickName;
    @NotNull(message = "{account.register.password.notNull}")
    @Length(min = 6, max = 12, message = "{account.register.password.length}")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
