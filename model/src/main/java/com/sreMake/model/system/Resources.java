package com.sreMake.model.system;

import com.sreMake.model.BaseEntity;
import org.babyfish.jimmer.sql.*;
import org.jetbrains.annotations.Nullable;

@Entity
@Table(name = "srm_resources")
public interface Resources extends BaseEntity {
    String resources();

    @Nullable
    String action();

    Type type();

    @Nullable
    @ManyToOne
    @JoinColumn(foreignKeyType = ForeignKeyType.FAKE)
    Resources parent();

    enum Type {
        ROUTER,
        BUTTON,
        MENU
    }

}
