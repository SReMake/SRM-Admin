package com.SReMake.model.user;

import org.babyfish.jimmer.sql.*;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

@Entity
@Table(name = "srm_user")
public interface User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    Status status();

    @Nullable
    @OneToOne
    User createBy();

    @Nullable
    @OneToOne
    User updateBy();

    public enum Status {
        /**
         * 正常
         */
        NORMAL,
        /**
         * 注销
         */
        DISABLE
    }
}

