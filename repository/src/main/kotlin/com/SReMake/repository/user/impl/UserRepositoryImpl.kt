package com.SReMake.repository.user.impl

import com.SReMake.model.user.*
import com.SReMake.model.user.dto.UserSearchInput
import com.SReMake.repository.user.UserRepository
import org.babyfish.jimmer.spring.repo.PageParam
import org.babyfish.jimmer.spring.repo.support.AbstractKotlinRepository
import org.babyfish.jimmer.sql.ast.LikeMode
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.*
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(sql: KSqlClient) : UserRepository, AbstractKotlinRepository<User, Long>(sql) {
    override fun findPage(pageParam: PageParam, params: UserSearchInput?) =
        params?.let {
            this.sql.createQuery(User::class) {
                where(
                    table.id.`eq?`(params.id),
                    table.username.`ilike?`(params.username, LikeMode.ANYWHERE),
                    table.status.`eq?`(params.status),
                    table.phone.`ilike?`(params.phone, LikeMode.ANYWHERE),
                    table.createAt.`between?`(params.createAtStart, params.createAtEnd),
                    table.updateAt.`between?`(params.updateAtStart, params.updateAtEnd)
                )
                orderBy(table.id.desc())
                select(table)
            }.fetchPage(pageParam.index, pageParam.size)
        } ?: this.findPage(pageParam);


    override fun findByUsername(username: String) =
        this.sql.createQuery(User::class) {
            where(
                table.username.eq(username),
                table.status.eq(Status.Normal)
            )
            select(table)
        }.fetchOne()


}