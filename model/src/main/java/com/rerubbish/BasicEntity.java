package com.rerubbish;

import com.rerubbish.user.User;
import org.babyfish.jimmer.sql.GeneratedValue;
import org.babyfish.jimmer.sql.GenerationType;
import org.babyfish.jimmer.sql.Id;

import java.time.LocalDateTime;

public interface BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();

    LocalDateTime createAt();

    LocalDateTime updateAt();

    User CreateBy();

    User UpdateBy();
}
