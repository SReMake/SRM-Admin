package com.SReMake.system.service.impl;

import com.SReMake.common.exception.can.ValidationException;
import com.SReMake.model.system.Resources;
import com.SReMake.model.system.dto.ResourcesInput;
import com.SReMake.model.user.Role;
import com.SReMake.model.user.User;
import com.SReMake.repository.system.CasbinRuleRepository;
import com.SReMake.repository.system.ResourcesRepository;
import com.SReMake.repository.system.RoleResourcesRepository;
import com.SReMake.repository.user.RoleRepository;
import com.SReMake.system.service.ResourcesService;
import com.SReMake.system.vo.ResourcesVo;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ResourcesServiceImpl implements ResourcesService {

    private final ResourcesRepository resourcesRepository;
    private final CasbinRuleRepository casbinRuleRepository;
    private final RoleResourcesRepository roleResourcesRepository;
    private final RoleRepository roleRepository;

    public ResourcesServiceImpl(ResourcesRepository resourcesRepository, CasbinRuleRepository casbinRuleRepository, RoleResourcesRepository roleResourcesRepository, RoleRepository roleRepository) {
        this.resourcesRepository = resourcesRepository;
        this.casbinRuleRepository = casbinRuleRepository;
        this.roleResourcesRepository = roleResourcesRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @CacheEvict(value = "resources", allEntries = true)
    public void addResources(ResourcesInput params) {
        resourcesRepository.insert(params);
    }

    @Override
    @CacheEvict(value = "resources", allEntries = true)
    public void deleteResources(long id) {

        Resources resources = resourcesRepository.findById(id);
        if (Objects.isNull(resources)) {
            return;
        }
//        校验casbin规则
        if (resources.type() == Resources.Type.ROUTER && casbinRuleRepository.countMatches(resources.resources()) > 0) {
            throw new ValidationException("the route is referenced and cannot be deleted");
        }
        resourcesRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "resources", key = "'resources_'+#user.id()")
    public List<ResourcesVo> listResources(@NotNull User user, List<String> roles) {
        if (user.username().equals("admin") || roles.contains("administrator")) {
            return resourcesRepository.findAll().stream().map(ResourcesVo::new).toList();
        } else {
            return roleResourcesRepository.listByRole(roleRepository.listByNames(roles).stream().map(Role::id).toList()).stream().map(ResourcesVo::new).toList();
        }

    }
}
