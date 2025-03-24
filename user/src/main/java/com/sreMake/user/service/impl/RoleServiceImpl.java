package com.sreMake.user.service.impl;

import com.sreMake.common.exception.can.ValidationException;
import com.sreMake.model.Immutables;
import com.sreMake.model.user.Role;
import com.sreMake.model.user.RoleDraft;
import com.sreMake.model.user.dto.RoleSearchInput;
import com.sreMake.model.user.dto.UpdateRoleInput;
import com.sreMake.repository.user.impl.RoleRepositoryImpl;
import com.sreMake.user.service.RoleService;
import org.babyfish.jimmer.Page;
import org.babyfish.jimmer.spring.repo.PageParam;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepositoryImpl roleRepositoryImpl;
    private final Enforcer enforcer;

    public RoleServiceImpl(RoleRepositoryImpl roleRepositoryImpl, Enforcer enforcer) {
        this.roleRepositoryImpl = roleRepositoryImpl;
        this.enforcer = enforcer;
    }

    @Override
    public void addRole(UpdateRoleInput params) {
        roleRepositoryImpl.insert(Immutables.createRole(
                draft -> {
                    draft.setName(params.getName());
                }
        ));
    }

    @Override
    public void deleteRole(long id) {

        Role role = roleRepositoryImpl.findById(id);
        if (Objects.isNull(role)) {
            return;
        }

        if (!enforcer.getUsersForRole(role.name()).isEmpty()) {
            throw new ValidationException("The current role has been referenced and cannot be deleted.");
        }

        roleRepositoryImpl.deleteById(id);
    }

    @Override
    public void updateRole(long id, UpdateRoleInput params) {
        roleRepositoryImpl.update(RoleDraft.$.produce(draft -> {
            draft.setId(id);
            if (!Objects.isNull(params.getName())) {
                draft.setName(params.getName());
            }
        }));
    }

    @Override
    public Page<Role> listRole(PageParam pageParam, RoleSearchInput params) {
        return roleRepositoryImpl.findPage(pageParam, params);
    }
}
