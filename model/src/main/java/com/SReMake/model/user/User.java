package com.SReMake.model.user;

import org.babyfish.jimmer.sql.*;

import java.time.LocalDateTime;

@Entity
public interface User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();

    LocalDateTime createAt();

    LocalDateTime updateAt();

    @OneToOne
    User createBy();

    @OneToOne
    User updateBy();

    String username();

    String password();

    String phone();

    String avatar();

    UserStatus status();

}

