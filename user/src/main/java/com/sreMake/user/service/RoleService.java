package com.sreMake.user.service;

import com.sreMake.model.user.Role;
import com.sreMake.model.user.dto.RoleSearchInput;
import com.sreMake.model.user.dto.UpdateRoleInput;
import org.babyfish.jimmer.Page;
import org.babyfish.jimmer.spring.repo.PageParam;

public interface RoleService {
    /**
     * 添加角色
     */
    void addRole(UpdateRoleInput params);

    /**
     * 删除角色
     */
    void deleteRole(long id);

    /**
     * 更新角色
     */
    void updateRole(long id, UpdateRoleInput params);

    /**
     * 获取角色列表
     */
    Page<Role> listRole(PageParam pageParam, RoleSearchInput params);

}
