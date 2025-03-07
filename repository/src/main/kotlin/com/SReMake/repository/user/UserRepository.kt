package com.SReMake.repository.user

import com.SReMake.model.user.User
import com.SReMake.model.user.dto.UserSearchInput
import org.babyfish.jimmer.Page
import org.babyfish.jimmer.spring.repo.KotlinRepository
import org.babyfish.jimmer.spring.repo.PageParam


interface UserRepository : KotlinRepository<User, Long> {
    /**
     * 分页查询
     */
    fun findPage(pageParam: PageParam, params: UserSearchInput?): Page<User>

    fun findByUsername(username: String): User?
}