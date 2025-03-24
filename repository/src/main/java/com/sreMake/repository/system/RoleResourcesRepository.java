package com.sreMake.repository.system;

import com.sreMake.model.system.Resources;
import com.sreMake.model.system.RoleResources;
import org.babyfish.jimmer.spring.repo.JavaRepository;

import java.util.Collection;
import java.util.List;

public interface RoleResourcesRepository extends JavaRepository<RoleResources, Long> {
    /**
     * 根据角色获取资源列表
     */
    List<Resources> listByRole(Collection<Long> roleIds);

    /**
     * 根据角色id与删除对应的资源id
     */
    void deleteByRoleIdAndResourceId(Long roleId, Collection<Long> resourceId);
}
