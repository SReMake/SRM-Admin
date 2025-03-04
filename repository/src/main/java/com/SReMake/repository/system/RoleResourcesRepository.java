package com.SReMake.repository.system;

import com.SReMake.model.system.Resources;
import com.SReMake.model.system.RoleResources;
import org.babyfish.jimmer.spring.repo.JavaRepository;

import java.util.Collection;
import java.util.List;

public interface RoleResourcesRepository extends JavaRepository<RoleResources, Long> {
    /**
     * 根据角色获取资源列表
     */
    List<Resources> listByRole(Collection<Long> roleIds);
}
