package com.sreMake.model.system;

import org.babyfish.jimmer.sql.*;
import org.jetbrains.annotations.Nullable;

@Entity
@Table(name = "casbin_rule")
public interface CasbinRule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id();

    String ptype();

    @Nullable
    String v0();

    @Nullable
    String v1();

    @Nullable
    String v2();

    @Nullable
    String v3();

    @Nullable
    String v4();

    @Nullable
    String v5();

}
