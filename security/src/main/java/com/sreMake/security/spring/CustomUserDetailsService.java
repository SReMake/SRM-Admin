package com.sreMake.security.spring;


import com.sreMake.model.security.CustomUserDetails;
import com.sreMake.model.user.User;
import com.sreMake.repository.user.UserRepository;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final Enforcer enforcer;

    public CustomUserDetailsService(UserRepository userRepository, Enforcer enforcer) {
        this.userRepository = userRepository;
        this.enforcer = enforcer;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        return new CustomUserDetails() {
            @Override
            public String getUserId() {
                return user.username();
            }

            @Override
            public User getUser() {
                return user;
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return enforcer.getRolesForUser(String.valueOf(user.id())).stream().map(SimpleGrantedAuthority::new).toList();
            }

            @Override
            public String getPassword() {
                return user.password();
            }

            @Override
            public String getUsername() {
                return user.username();
            }
        };

    }
}