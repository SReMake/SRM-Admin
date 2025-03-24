package com.sreMake.repository.user;

import com.sreMake.model.user.User;
import com.sreMake.model.user.dto.UserSearchInput;
import org.babyfish.jimmer.Page;
import org.babyfish.jimmer.spring.repo.JavaRepository;
import org.babyfish.jimmer.spring.repo.PageParam;

public interface UserRepository extends JavaRepository<User, Long> {
    /**
     * 分页查询
     */
    Page<User> findPage(PageParam pageParam, UserSearchInput params);

    User findByUsername(String username);
}
