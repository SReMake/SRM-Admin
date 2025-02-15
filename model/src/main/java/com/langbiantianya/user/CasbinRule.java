package com.langbiantianya.user;

import org.babyfish.jimmer.sql.Entity;
import org.babyfish.jimmer.sql.GeneratedValue;
import org.babyfish.jimmer.sql.GenerationType;
import org.babyfish.jimmer.sql.Id;

@Entity
public interface CasbinRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();

    String ptype();

    String v0();

    String v1();

    String v2();

    String v3();

    String v4();

    String v5();

}
