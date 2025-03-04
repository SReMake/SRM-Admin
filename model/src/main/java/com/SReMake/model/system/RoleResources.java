package com.SReMake.model.system;

import com.SReMake.model.BaseEntity;
import com.SReMake.model.user.Role;
import org.babyfish.jimmer.sql.Entity;
import org.babyfish.jimmer.sql.OneToOne;
import org.babyfish.jimmer.sql.Table;

@Entity
@Table(name = "srm_role_resources")
public interface RoleResources extends BaseEntity {
    @OneToOne
    Role role();

    @OneToOne
    Resources resources();
}
