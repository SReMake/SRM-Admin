package com.sreMake.system.service.impl;

import com.sreMake.common.exception.can.AuthenticationException;
import com.sreMake.common.exception.can.ValidationException;
import com.sreMake.model.system.Resources;
import com.sreMake.model.system.dto.ResourcesInput;
import com.sreMake.model.user.Role;
import com.sreMake.model.user.User;
import com.sreMake.repository.system.CasbinRuleRepository;
import com.sreMake.repository.system.ResourcesRepository;
import com.sreMake.repository.system.RoleResourcesRepository;
import com.sreMake.repository.user.RoleRepository;
import com.sreMake.system.service.ResourcesService;
import com.sreMake.system.vo.ApiVo;
import com.sreMake.system.vo.ResourcesVo;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class ResourcesServiceImpl implements ResourcesService {

    private final ResourcesRepository resourcesRepository;
    private final CasbinRuleRepository casbinRuleRepository;
    private final RoleResourcesRepository roleResourcesRepository;
    private final RoleRepository roleRepository;
    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    public ResourcesServiceImpl(ResourcesRepository resourcesRepository, CasbinRuleRepository casbinRuleRepository, RoleResourcesRepository roleResourcesRepository, RoleRepository roleRepository, RequestMappingHandlerMapping requestMappingHandlerMapping) {
        this.resourcesRepository = resourcesRepository;
        this.casbinRuleRepository = casbinRuleRepository;
        this.roleResourcesRepository = roleResourcesRepository;
        this.roleRepository = roleRepository;
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
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
            return roleResourcesRepository.listByRole(roleRepository.listByNames(roles).stream().map(Role::id).toList())
                    .stream().filter(resources -> resources.type() != Resources.Type.ROUTER)
                    .map(ResourcesVo::new)
                    .toList();
        }

    }

    @Override
    public List<ApiVo> listApis(@NotNull User user, List<String> roles) {
        if (user.username().equals("admin") || roles.contains("administrator")) {
            List<ApiVo> apis = new java.util.ArrayList<>();
            requestMappingHandlerMapping.getHandlerMethods().forEach((method, handler) -> {
                if (!method.getDirectPaths().isEmpty()) {
                    apis.add(new ApiVo(
                            method.getMethodsCondition().getMethods().stream().map(Enum::name).collect(Collectors.toSet()),
                            method.getDirectPaths()
                    ));
                }
            });
            return apis;
        } else {
            throw new AuthenticationException("");
        }
    }
}
