package com.sreMake.model.system;

import com.sreMake.model.BaseEntity;
import com.sreMake.model.user.Role;
import org.babyfish.jimmer.sql.Entity;
import org.babyfish.jimmer.sql.Key;
import org.babyfish.jimmer.sql.ManyToOne;
import org.babyfish.jimmer.sql.Table;

@Entity
@Table(name = "srm_role_resources")
public interface RoleResources extends BaseEntity {
    @ManyToOne
    @Key
    Role role();

    @ManyToOne
    @Key
    Resources resources();
}
