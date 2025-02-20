package com.SReMake.model.user;

import org.babyfish.jimmer.sql.*;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

@Entity
@Table(name = "srm_user")
public interface User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();

    String salt();

    LocalDateTime createAt();

    @Nullable
    LocalDateTime updateAt();

    String username();

    String password();

    @Nullable
    String phone();

    @Nullable
    String avatar();

    UserStatus status();

    @Nullable
    @OneToOne
    User createBy();

    @Nullable
    @OneToOne
    User updateBy();
}

