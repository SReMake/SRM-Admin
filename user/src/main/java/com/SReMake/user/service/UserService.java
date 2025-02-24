package com.SReMake.user.service;

import com.SReMake.model.user.dto.UpdateUserInput;
import com.SReMake.model.user.dto.UserInput;
import com.SReMake.model.user.dto.UserSearchInput;
import com.SReMake.user.vo.UserVo;
import org.babyfish.jimmer.Page;
import org.babyfish.jimmer.spring.repo.PageParam;

public interface UserService {

    /**
     * 添加用户
     */
    void addUser(UserInput user);

    /**
     * 查看用户列表
     */
    Page<UserVo> listUser(PageParam pageParam, UserSearchInput params);


    /**
     * 更新用户信息
     */
    void updateUser(long id, UpdateUserInput user);

    /**
     * 禁用账户
     */
    void disableUser(long id);

    /**
     * 启用账户
     */
    void enableUser(long id);
}
