package com.mondris.demo.Util.Config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    // to configure authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    }



    // to config authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .authorizeRequests()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated();
    }
}
