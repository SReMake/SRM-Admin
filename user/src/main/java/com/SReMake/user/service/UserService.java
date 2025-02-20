package com.SReMake.user.service;

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
    Page<UserVo> listUser(PageParam pageParam ,UserSearchInput params);

}
