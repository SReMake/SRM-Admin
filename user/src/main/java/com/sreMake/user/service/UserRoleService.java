package com.sreMake.user.service;

import com.sreMake.model.user.User;
import com.sreMake.model.user.dto.UserRoleInput;
import com.sreMake.user.vo.RoleVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRoleService {
    /**
     * 给用户赋予角色
     */
    void addUserRoles(long userId, List<Long> roleIds);

    /**
     * 删除用户的角色
     */
    void deleteUserRoles(long userId, List<Long> roleIds);

    /**
     * 更新用户角色谢谢
     */
    @Transactional
    void updateUserRoles(long userId, UserRoleInput params);

    /**
     * 查看当前用户角色
     */
    List<RoleVo> listUserRole(User user);
}
