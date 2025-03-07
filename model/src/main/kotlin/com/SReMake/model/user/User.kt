package com.SReMake.model.user

import com.SReMake.model.BaseEntity
import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Table

@Entity
@Table(name = "srm_user")
interface User : BaseEntity {
    val username: String
    val password: String
    val email: String?
    val phone: String?
    val avatar: String?
    val status: Status


}
enum class Status {
    /**
     * 正常
     */
    Normal,

    /**
     * 停用
     */
    Disable
}
