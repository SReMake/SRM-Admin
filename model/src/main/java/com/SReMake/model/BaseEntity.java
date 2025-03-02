package com.SReMake.model;

import com.SReMake.model.user.User;
import org.babyfish.jimmer.sql.*;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

@MappedSuperclass
public interface BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();


    LocalDateTime createAt();

    @Nullable
    LocalDateTime updateAt();

    @Nullable
    @ManyToOne
    User createBy();

    @Nullable
    @ManyToOne
    User updateBy();
}