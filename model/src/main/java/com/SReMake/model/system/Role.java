package com.SReMake.model.system;

import com.SReMake.model.user.User;
import org.babyfish.jimmer.sql.*;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

@Entity
@Table(name = "srm_role")
public interface Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();

    LocalDateTime createAt();

    @Nullable
    LocalDateTime updateAt();

    String name();

    @Nullable
    @OneToOne
    User createBy();

    @Nullable
    @OneToOne
    User updateBy();
}
