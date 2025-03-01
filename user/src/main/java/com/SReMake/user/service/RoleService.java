package com.SReMake.user.service;

import com.SReMake.model.user.Role;
import com.SReMake.model.user.User;
import com.SReMake.model.user.dto.RoleSearchInput;
import com.SReMake.model.user.dto.UpdateRoleInput;
import org.babyfish.jimmer.Page;
import org.babyfish.jimmer.spring.repo.PageParam;

public interface RoleService {
    /**
     * 添加角色
     */
    void addRole(User user, UpdateRoleInput params);

    /**
     * 删除角色
     */
    void deleteRole(long id);

    /**
     * 更新角色
     */
    void updateRole(long id,User user ,UpdateRoleInput params);

    /**
     * 获取角色列表
     */
    Page<Role> listRole(PageParam pageParam, RoleSearchInput params);

}
