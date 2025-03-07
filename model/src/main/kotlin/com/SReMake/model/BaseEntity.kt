package com.SReMake.model

import com.SReMake.model.user.User
import org.babyfish.jimmer.sql.*
import java.time.LocalDateTime

@MappedSuperclass
interface BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

    val createAt: LocalDateTime
    val updateAt: LocalDateTime?

    @ManyToOne
    val createBy: User?

    @ManyToOne
    val updateBy: User?
}