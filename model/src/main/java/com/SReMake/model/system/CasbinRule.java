package com.SReMake.model.system;

import com.SReMake.model.user.User;
import org.babyfish.jimmer.sql.*;

import java.time.LocalDateTime;

@Entity
public interface CasbinRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();

    LocalDateTime createAt();

    LocalDateTime updateAt();

    @OneToOne
    User createBy();

    @OneToOne
    User updateBy();

    String ptype();

    String v0();

    String v1();

    String v2();

    String v3();

    String v4();

    String v5();

}
