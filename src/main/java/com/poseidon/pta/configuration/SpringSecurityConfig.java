package com.poseidon.pta.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.InetAddress;

/**
 * SpringSecurity Configuration
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${frontend.app.ip}")
    private String authIp;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        String security = "hasIpAddress('127.0.0.1') or hasIpAddress('::1') or hasIpAddress('" + authIp + "') or isAuthenticated()";

        httpSecurity.authorizeRequests()
                .antMatchers("/**")
                //.access("hasIpAddress('127.0.0.1') or hasIpAddress('::1') or isAuthenticated()")
                .access(security)
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

