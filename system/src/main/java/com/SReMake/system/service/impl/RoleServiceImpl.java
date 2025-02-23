package com.SReMake.system.service.impl;

import com.SReMake.model.system.Role;
import com.SReMake.model.system.RoleDraft;
import com.SReMake.model.system.dto.RoleSearchInput;
import com.SReMake.model.system.dto.UpdateRoleInput;
import com.SReMake.repository.system.impl.RoleRepositoryImpl;
import com.SReMake.system.service.RoleService;
import org.babyfish.jimmer.Page;
import org.babyfish.jimmer.spring.repo.PageParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepositoryImpl roleRepositoryImpl;

    public RoleServiceImpl(RoleRepositoryImpl roleRepositoryImpl) {
        this.roleRepositoryImpl = roleRepositoryImpl;
    }

    @Override
    public void addRole(UpdateRoleInput role) {
        roleRepositoryImpl.insert(role);
    }

    @Override
    public void deleteRole(long id) {
//        TODO: 判断是否被引用 被引用的话禁止删除
        roleRepositoryImpl.deleteById(id);
    }

    @Override
    public void updateRole(long id, UpdateRoleInput role) {
        roleRepositoryImpl.update(RoleDraft.$.produce(draft -> {
            draft.setId(id);
            if (!Objects.isNull(role.getName())) {
                draft.setName(role.getName());
            }
        }));
    }

    @Override
    public Page<Role> listRole(PageParam pageParam, RoleSearchInput params) {
        return roleRepositoryImpl.findPage(pageParam, params);
    }
}
