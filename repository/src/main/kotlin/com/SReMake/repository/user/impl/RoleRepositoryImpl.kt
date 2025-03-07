package com.SReMake.repository.user.impl

import com.SReMake.model.user.*
import com.SReMake.model.user.dto.RoleSearchInput
import com.SReMake.repository.user.RoleRepository
import org.babyfish.jimmer.spring.repo.PageParam
import org.babyfish.jimmer.spring.repo.support.AbstractKotlinRepository
import org.babyfish.jimmer.sql.ast.LikeMode
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.desc
import org.babyfish.jimmer.sql.kt.ast.expression.`ilike?`
import org.babyfish.jimmer.sql.kt.ast.expression.valueIn
import org.springframework.stereotype.Repository

@Repository
class RoleRepositoryImpl(sql: KSqlClient) : RoleRepository, AbstractKotlinRepository<Role, Long>(sql) {
    override fun findPage(pageParam: PageParam, params: RoleSearchInput) =
        this.sql.createQuery(Role::class) {
            where(table.name.`ilike?`(params.name, LikeMode.ANYWHERE))
            orderBy(table.id.desc())
            select(table.fetchBy {
                allScalarFields()
                createBy {
                    User {
                        username
                    }
                }
                updateBy {
                    User {
                        username
                    }
                }

            })
        }.fetchPage(pageParam.index, pageParam.size)

    override fun listByNames(names: Collection<String>) =
        this.sql.createQuery(Role::class) {
            where(table.name.valueIn(names))
            select(table)
        }.execute()

}