package com.SReMake.model.system;

import com.SReMake.model.user.User;
import org.babyfish.jimmer.sql.*;

import java.time.LocalDateTime;

@Entity
public interface Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();

    LocalDateTime createAt();

    LocalDateTime updateAt();

    @OneToOne
    User createBy();

    @OneToOne
    User updateBy();

    String name();
}
