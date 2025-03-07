package com.SReMake.repository.system

import com.SReMake.model.system.Resources
import org.babyfish.jimmer.spring.repo.KotlinRepository

interface ResourcesRepository : KotlinRepository<Resources, Long> {
    /**
     * 通过路由路径获取Resources
     */
    fun listByRouters(routers: Collection<String>): List<Resources>

    /**
     * parent 获取全部相关的父级
     */
    fun listParentId(parentIds: Collection<Long>): List<Resources>
}