package com.SReMake.repository.system.impl

import com.SReMake.model.system.RoleResources
import com.SReMake.model.system.resource
import com.SReMake.model.system.resourceId
import com.SReMake.model.system.roleId
import com.SReMake.repository.system.RoleResourcesRepository
import org.babyfish.jimmer.spring.repo.support.AbstractKotlinRepository
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.valueIn
import org.springframework.stereotype.Repository

@Repository
class RoleResourcesRepositoryImpl(sql: KSqlClient) : RoleResourcesRepository,
    AbstractKotlinRepository<RoleResources, Long>(sql) {
    override fun listByRole(roleIds: Collection<Long>) =
        this.sql.createQuery(RoleResources::class) {
            where(table.roleId.valueIn(roleIds))
            select(table.resource)
        }.execute()

    override fun deleteByRoleIdAndResourceId(roleId: Long, resourceIds: Collection<Long>) {
        this.sql.createDelete(RoleResources::class) {
            where(table.roleId.eq(roleId), table.resourceId.valueIn(resourceIds))
        }.execute()
    }

}