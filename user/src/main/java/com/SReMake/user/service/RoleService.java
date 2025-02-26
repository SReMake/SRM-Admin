package com.SReMake.user.service;

import com.SReMake.model.system.Role;
import com.SReMake.model.system.dto.RoleSearchInput;
import com.SReMake.model.system.dto.UpdateRoleInput;
import org.babyfish.jimmer.Page;
import org.babyfish.jimmer.spring.repo.PageParam;

public interface RoleService {
    /**
     * 添加角色
     */
    void addRole(UpdateRoleInput role);

    /**
     * 删除角色
     */
    void deleteRole(long id);

    /**
     * 更新角色
     */
    void updateRole(long id, UpdateRoleInput role);

    /**
     * 获取角色列表
     */
    Page<Role> listRole(PageParam pageParam, RoleSearchInput params);

}
