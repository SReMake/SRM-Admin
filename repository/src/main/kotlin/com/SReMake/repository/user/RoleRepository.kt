package com.SReMake.repository.user

import com.SReMake.model.user.Role
import com.SReMake.model.user.dto.RoleSearchInput
import org.babyfish.jimmer.Page
import org.babyfish.jimmer.spring.repo.KotlinRepository
import org.babyfish.jimmer.spring.repo.PageParam

interface RoleRepository : KotlinRepository<Role, Long> {
    /**
     * 分页查询
     */
    fun findPage(pageParam: PageParam, params: RoleSearchInput): Page<Role>

    /**
     * 根据角色名查询
     */
    fun listByNames(names: Collection<String>): List<Role>
}