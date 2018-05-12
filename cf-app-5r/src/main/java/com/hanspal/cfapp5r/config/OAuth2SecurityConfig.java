package com.hanspal.cfapp5r.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class OAuth2SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring()
                .mvcMatchers("/favicon.ico", "/webjars/**", "/static/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/secured/**").authenticated()
                .antMatchers("/", "/custom_login").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login().loginPage("/custom_login");
    }

}

