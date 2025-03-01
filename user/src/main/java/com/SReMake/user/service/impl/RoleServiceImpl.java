package com.SReMake.user.service.impl;

import com.SReMake.common.exception.can.ValidationException;
import com.SReMake.model.Immutables;
import com.SReMake.model.user.Role;
import com.SReMake.model.user.RoleDraft;
import com.SReMake.model.user.User;
import com.SReMake.model.user.dto.RoleSearchInput;
import com.SReMake.model.user.dto.UpdateRoleInput;
import com.SReMake.repository.user.impl.RoleRepositoryImpl;
import com.SReMake.user.service.RoleService;
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
    public void addRole(User user, UpdateRoleInput params) {
        roleRepositoryImpl.insert(Immutables.createRole(
                draft -> {
                    draft.setName(params.getName());
                    draft.setCreateBy(user);
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
    public void updateRole(long id, User user, UpdateRoleInput params) {
        roleRepositoryImpl.update(RoleDraft.$.produce(draft -> {
            draft.setId(id);
            if (!Objects.isNull(params.getName())) {
                draft.setName(params.getName());
                draft.setUpdateBy(user);
            }
        }));
    }

    @Override
    public Page<Role> listRole(PageParam pageParam, RoleSearchInput params) {
        return roleRepositoryImpl.findPage(pageParam, params);
    }
}
