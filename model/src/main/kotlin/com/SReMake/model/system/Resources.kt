package com.SReMake.model.system

import com.SReMake.model.BaseEntity
import org.babyfish.jimmer.sql.*

@Entity
@Table(name = "srm_resources")
interface Resources : BaseEntity {
    val resources: String
    val action: String?
    val type: Type

    @ManyToOne
    @JoinColumn(foreignKeyType = ForeignKeyType.FAKE)
    val parent: Resources?


}

enum class Type {
    ROUTER,
    BUTTON,
    MENU
}