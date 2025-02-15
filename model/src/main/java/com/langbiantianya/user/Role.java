package com.langbiantianya.user;

import org.babyfish.jimmer.sql.*;

@Entity
public interface Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id();

    @ManyToOne()
    User user();

    long role();
}
