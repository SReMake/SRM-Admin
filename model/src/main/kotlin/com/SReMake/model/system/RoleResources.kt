package com.SReMake.model.system

import com.SReMake.model.BaseEntity
import com.SReMake.model.user.Role
import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Key
import org.babyfish.jimmer.sql.ManyToOne
import org.babyfish.jimmer.sql.Table

@Entity
@Table(name = "srm_role_resources")
interface RoleResources : BaseEntity {
    @ManyToOne
    @Key
    val role: Role

    @ManyToOne
    @Key
    val resource: Resources
}