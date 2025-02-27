package com.SReMake.model.ext;

import com.SReMake.model.user.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetails extends UserDetails {
    String getUserId();

    User getUser();
}
