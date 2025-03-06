package com.SReMake.model.system;

import com.SReMake.model.BaseEntity;
import com.SReMake.model.user.Role;
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
