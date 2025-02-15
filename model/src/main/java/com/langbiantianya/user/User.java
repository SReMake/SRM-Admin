package com.langbiantianya.user;

import org.babyfish.jimmer.sql.*;

import java.util.List;

@Entity
public interface User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id();

    String username();

    String password();

    String phone();

    String avatar();

    UserStatus status();

    @OneToMany(mappedBy = "user")
    List<Role> roles();
}

