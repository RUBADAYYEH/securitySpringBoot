package com.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@EnableAutoConfiguration
public interface UserService {
    @Autowired
    UserDetailsService userDetailsService();
    // it is an interface that retrives user authentication
    // and authorization information. It has only one method
    // loadUserByUsername(), which can be implemented to
    // supply user information to Spring Security API
}
