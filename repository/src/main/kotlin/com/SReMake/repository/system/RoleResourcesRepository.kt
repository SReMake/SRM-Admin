package com.SReMake.repository.system

import com.SReMake.model.system.Resources
import com.SReMake.model.system.RoleResources
import org.babyfish.jimmer.spring.repo.KotlinRepository

interface RoleResourcesRepository : KotlinRepository<RoleResources, Long> {
    /**
     * 根据角色获取资源列表
     */
    fun listByRole(roleIds: Collection<Long>): List<Resources>

    /**
     * 根据角色id与删除对应的资源id
     */

    fun deleteByRoleIdAndResourceId(roleId: Long, resourceIds: Collection<Long>)
}