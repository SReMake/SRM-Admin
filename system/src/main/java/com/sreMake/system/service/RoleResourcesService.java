package com.sreMake.system.service;

import java.util.List;

public interface RoleResourcesService {

    /**
     * 授权角色资源
     */
    void grantRoleResources(long roleId, List<Long> resourcesIds);

    /**
     * 撤销角色资源授权
     */
    void revokeRoleResources(long roleId, List<Long> resourcesIds);
}
