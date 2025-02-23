package com.SReMake.security.spring;


import com.SReMake.model.user.User;
import com.SReMake.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class CustomerUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository ;

    public CustomerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        return  new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
//                TODO casbin查找用户角色
                return List.of();
            }

            @Override
            public String getPassword() {
                return user.password();
            }

            @Override
            public String getUsername() {
                return user.username();
            }
        } ;

    }
}