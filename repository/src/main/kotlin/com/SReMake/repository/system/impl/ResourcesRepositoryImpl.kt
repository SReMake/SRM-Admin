package com.SReMake.repository.system.impl

import com.SReMake.model.system.Resources
import com.SReMake.model.system.parentId
import com.SReMake.model.system.resources
import com.SReMake.repository.system.ResourcesRepository
import org.babyfish.jimmer.spring.repo.support.AbstractKotlinRepository
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.valueIn
import org.springframework.stereotype.Repository

@Repository
class ResourcesRepositoryImpl(sql: KSqlClient) : ResourcesRepository, AbstractKotlinRepository<Resources, Long>(sql) {
    override fun listByRouters(routers: Collection<String>): List<Resources> =
        this.sql.createQuery(Resources::class) {
            where(table.resources.valueIn(routers))
            select(table)
        }.execute()


    override fun listParentId(parentIds: Collection<Long>) =
        this.sql.createQuery(Resources::class) {
            where(table.parentId.valueIn(parentIds))
            select(table)
        }.execute()

}