package com.sreMake.model.user;

import com.sreMake.model.BaseEntity;
import org.babyfish.jimmer.sql.Entity;
import org.babyfish.jimmer.sql.Table;

@Entity
@Table(name = "srm_role")
public interface Role extends BaseEntity {

    String name();

}
