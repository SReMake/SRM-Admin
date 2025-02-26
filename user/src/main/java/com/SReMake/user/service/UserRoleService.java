package com.SReMake.user.service;

import com.SReMake.model.system.Role;
import com.SReMake.model.user.User;

import java.util.List;

public interface UserRoleService {
    /**
     * 给用户赋予角色
     */
    void addUserRoles(long userId, List<Long> roleIds);

    /**
     * 删除用户的角色
     */
    void deleteUserRoles(long userId, List<Long> roleId);

    /**
     * 查看当前用户角色
     */
    List<Role> listUserRole(User user);
}
