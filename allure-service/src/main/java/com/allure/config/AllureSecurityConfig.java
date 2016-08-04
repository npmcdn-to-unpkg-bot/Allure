package com.allure.config;

import com.allure.http.ApiResponse;
import com.allure.http.response.account.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by yang_shoulai on 8/4/2016.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class AllureSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restEntryPoint())
                //.and()
                //.authorizeRequests()
               // .anyRequest().fullyAuthenticated()
                .and()
                .formLogin().loginProcessingUrl("/login").usernameParameter("email").passwordParameter("password")
                .successHandler(successHandler()).failureHandler(failureHandler())
                .and()
                .logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();
    }


    @Bean
    public AuthenticationEntryPoint restEntryPoint() {
        return (httpServletRequest, httpServletResponse, ex) -> {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.getWriter().write(new ApiResponse.Builder().exception().globalError(null, "Unauthorized").build().toJson());
        };
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            UserService.LoggedUser user = (UserService.LoggedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setId(user.getId());
            loginResponse.setEmail(user.getUsername());
            loginResponse.setNickName(user.getNickName());
            Collection<GrantedAuthority> authorities = user.getAuthorities();
            List<String> roles = new ArrayList<>();
            authorities.forEach(authority -> roles.add(authority.getAuthority()));
            loginResponse.setRoles(roles);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(new ApiResponse.Builder().success().result(loginResponse).build().toJson());
        };
    }

    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return (request, response, authentication) -> {
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(new ApiResponse.Builder().exception().result("login failed").build().toJson());
        };
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(new ApiResponse.Builder().success().build().toJson());
        };
    }

}
