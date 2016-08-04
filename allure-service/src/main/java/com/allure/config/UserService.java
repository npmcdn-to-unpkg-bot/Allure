package com.allure.config;

import com.allure.domain.model.Account;
import com.allure.domain.model.Role;
import com.allure.domain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by yang_shoulai on 8/4/2016.
 */
@Service
public class UserService implements UserDetailsService {


    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmailIgnoreCase(userName);
        if (account == null) {
            throw new UsernameNotFoundException(userName);
        }
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        Set<Role> roleSet = account.getRoles();
        roleSet.forEach(role -> roles.add(new SimpleGrantedAuthority(role.getRole())));
        return new LoggedUser(account.getId(), account.getNickName(), userName, account.getPassword(), roles);
    }


    public static class LoggedUser extends User {

        private long id;

        private String nickName;

        public LoggedUser(long id, String nickName, String username, String password, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, authorities);
            this.id = id;
            this.nickName = nickName;
        }

        public long getId() {
            return id;
        }

        public String getNickName() {
            return nickName;
        }
    }
}
