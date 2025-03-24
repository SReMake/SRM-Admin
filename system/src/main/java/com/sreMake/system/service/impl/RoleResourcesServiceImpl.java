package com.sreMake.system.service.impl;

import com.sreMake.common.exception.can.ValidationException;
import com.sreMake.model.system.Resources;
import com.sreMake.model.system.RoleResourcesDraft;
import com.sreMake.model.user.Role;
import com.sreMake.repository.system.ResourcesRepository;
import com.sreMake.repository.system.RoleResourcesRepository;
import com.sreMake.repository.user.RoleRepository;
import com.sreMake.system.service.RoleResourcesService;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class RoleResourcesServiceImpl implements RoleResourcesService {
    private final ResourcesRepository resourcesRepository;
    private final RoleResourcesRepository roleResourcesRepository;
    private final RoleRepository roleRepository;

    private final Enforcer enforcer;


    public RoleResourcesServiceImpl(ResourcesRepository resourcesRepository, RoleResourcesRepository roleResourcesRepository, RoleRepository roleRepository, Enforcer enforcer) {
        this.resourcesRepository = resourcesRepository;
        this.roleResourcesRepository = roleResourcesRepository;
        this.roleRepository = roleRepository;
        this.enforcer = enforcer;
    }

    @Override
    @CacheEvict(value = "resources", allEntries = true)
    public void grantRoleResources(long roleId, List<Long> resourcesIds) {
        Role role = roleRepository.findById(roleId);
        if (Objects.isNull(role)) {
            throw new ValidationException("role not exist");
        }
        List<Resources> resources = resourcesRepository.findByIds(resourcesIds);

        List<Resources> routers = resources.stream().filter(res -> res.type() == Resources.Type.ROUTER).toList();

        enforcer.addGroupingPolicies(routers.stream().map(router -> List.of(role.name(), router.resources())).toList());

        roleResourcesRepository.saveEntities(resources.stream().map(res -> RoleResourcesDraft.$.produce(draft -> {
            draft.setRoleId(role.id());
            draft.setResourcesId(res.id());
        })).toList());

    }

    @Override
    @CacheEvict(value = "resources", allEntries = true)
    public void revokeRoleResources(long roleId, List<Long> resourcesIds) {
        Role role = roleRepository.findById(roleId);
        if (Objects.isNull(role)) {
            throw new ValidationException("role not exist");
        }
        if (resourcesIds.isEmpty()) {
            return;
        }
        List<Resources> resources = resourcesRepository.findByIds(resourcesIds);
        List<Resources> routers = resources.stream().filter(res -> res.type() == Resources.Type.ROUTER).toList();
        enforcer.removeGroupingPolicies(routers.stream().map(router -> List.of(role.name(), router.resources())).toList());
        roleResourcesRepository.deleteByRoleIdAndResourceId(role.id(), resources.stream().map(Resources::id).toList());
    }
}
