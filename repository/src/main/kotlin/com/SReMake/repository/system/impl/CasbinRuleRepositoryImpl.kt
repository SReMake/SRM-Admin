package com.SReMake.repository.system.impl

import com.SReMake.model.system.CasbinRule
import com.SReMake.model.system.v0
import com.SReMake.model.system.v1
import com.SReMake.repository.system.CasbinRuleRepository
import org.babyfish.jimmer.spring.repo.support.AbstractKotlinRepository
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.count
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.valueIn
import org.springframework.stereotype.Repository

@Repository
class CasbinRuleRepositoryImpl(sql: KSqlClient) : CasbinRuleRepository,
    AbstractKotlinRepository<CasbinRule, Int>(sql) {
    override fun countMatches(router: String) =
        this.sql.createQuery(CasbinRule::class) {
            where(table.v1.eq(router))
            select(count(table))
        }.fetchOne()


    override fun listMatchesRole(roles: Collection<String>): List<String> =
        this.sql.createQuery(CasbinRule::class) {
            where(table.v0.valueIn(roles))
            select(table.v1)
        }.execute().mapNotNull { it }

}