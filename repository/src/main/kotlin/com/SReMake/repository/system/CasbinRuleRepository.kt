package com.SReMake.repository.system

import com.SReMake.model.system.CasbinRule
import org.babyfish.jimmer.spring.repo.KotlinRepository

interface CasbinRuleRepository : KotlinRepository<CasbinRule, Int> {
    /**
     * 查询casbin中有多少匹配到的
     */
    fun countMatches(router: String): Long

    /**
     * 根据角色查询资源
     */

    fun listMatchesRole(roles: Collection<String>): List<String>
}