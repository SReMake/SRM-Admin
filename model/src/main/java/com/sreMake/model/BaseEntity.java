package com.sreMake.model;

import com.sreMake.model.user.User;
import org.babyfish.jimmer.sql.*;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;

@MappedSuperclass
public interface BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();


    OffsetDateTime createAt();

    @Nullable
    OffsetDateTime updateAt();

    @Nullable
    @ManyToOne
    User createBy();

    @Nullable
    @ManyToOne
    User updateBy();
}