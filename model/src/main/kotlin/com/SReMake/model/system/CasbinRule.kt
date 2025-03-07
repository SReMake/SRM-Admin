package com.SReMake.model.system

import org.babyfish.jimmer.sql.*

@Entity
@Table(name = "casbin_rule")
interface CasbinRule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int
    val ptype: String
    val v0: String?
    val v1: String?
    val v2: String?
    val v3: String?
    val v4: String?
    val v5: String?
}