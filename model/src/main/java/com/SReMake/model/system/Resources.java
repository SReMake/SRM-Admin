package com.SReMake.model.system;

import com.SReMake.model.BaseEntity;
import org.babyfish.jimmer.sql.Entity;
import org.babyfish.jimmer.sql.OneToOne;
import org.babyfish.jimmer.sql.Table;
import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "srm_resources")
public interface Resources extends BaseEntity {
    String resources();

    String action();

    Type type();

    @NotNull
    @OneToOne
    Resources parent();

    public enum Type {
        ROUTER,
        BUTTON,
        MENU
    }

}
