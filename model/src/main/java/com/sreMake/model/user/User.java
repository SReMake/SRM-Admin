package com.sreMake.model.user;

import com.sreMake.model.BaseEntity;
import org.babyfish.jimmer.sql.Entity;
import org.babyfish.jimmer.sql.Table;
import org.jetbrains.annotations.Nullable;

@Entity
@Table(name = "srm_user")
public interface User extends BaseEntity {


    String username();

    String password();

    @Nullable
    String phone();

    @Nullable
    String email();

    @Nullable
    String avatar();

    Status status();


    enum Status {
        /**
         * 正常
         */
        NORMAL,
        /**
         * 停用
         */
        DISABLE
    }
}

