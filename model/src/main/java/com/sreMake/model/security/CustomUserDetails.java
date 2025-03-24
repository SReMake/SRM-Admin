package com.sreMake.model.security;

import com.sreMake.model.user.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetails extends UserDetails {
    String getUserId();

    User getUser();
}
