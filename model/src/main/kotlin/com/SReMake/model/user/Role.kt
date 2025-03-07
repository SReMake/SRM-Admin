package com.SReMake.model.user

import com.SReMake.model.BaseEntity
import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Table

@Entity
@Table(name = "srm_role")
interface Role : BaseEntity {
    val name: String
}