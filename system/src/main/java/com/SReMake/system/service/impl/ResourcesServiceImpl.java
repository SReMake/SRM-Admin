package com.SReMake.system.service.impl;

import com.SReMake.common.exception.can.ValidationException;
import com.SReMake.model.system.Resources;
import com.SReMake.model.system.dto.ResourcesInput;
import com.SReMake.repository.system.CasbinRuleRepository;
import com.SReMake.repository.system.ResourcesRepository;
import com.SReMake.system.service.ResourcesService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ResourcesServiceImpl implements ResourcesService {

    private final ResourcesRepository resourcesRepository;
    private final CasbinRuleRepository casbinRuleRepository;

    public ResourcesServiceImpl(ResourcesRepository resourcesRepository, CasbinRuleRepository casbinRuleRepository) {
        this.resourcesRepository = resourcesRepository;
        this.casbinRuleRepository = casbinRuleRepository;
    }

    @Override
    public void addResources(ResourcesInput params) {
        resourcesRepository.insert(params);
    }

    @Override
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
}
