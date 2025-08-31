package com.sreMake.user.service;

import com.sreMake.model.user.dto.UpdateUserInput;
import com.sreMake.model.user.dto.UserInput;
import com.sreMake.model.user.dto.UserSearchInput;
import com.sreMake.user.vo.UserVo;
import org.babyfish.jimmer.Page;
import org.babyfish.jimmer.spring.repo.PageParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    /**
     * 添加用户
     */
    void addUser(UserInput params);

    /**
     * 查看用户列表
     */
    Page<UserVo> listUser(PageParam pageParam, UserSearchInput params);

    /**
     * 更新用户信息
     */
    @Transactional
    void updateUser(long id, UpdateUserInput params);

    /**
     * 禁用账户
     */
    void disableUser(long id);

    /**
     * 启用账户
     */
    void enableUser(long id);
}
