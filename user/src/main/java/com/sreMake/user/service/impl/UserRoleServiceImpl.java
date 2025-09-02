package com.sreMake.user.service.impl;

import com.sreMake.common.exception.can.ValidationException;
import com.sreMake.model.user.Role;
import com.sreMake.model.user.User;
import com.sreMake.model.user.UserDraft;
import com.sreMake.model.user.dto.UserRoleInput;
import com.sreMake.repository.user.RoleRepository;
import com.sreMake.repository.user.UserRepository;
import com.sreMake.user.service.UserRoleService;
import com.sreMake.user.vo.RoleVo;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {
    private final Enforcer enforcer;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;


    public UserRoleServiceImpl(Enforcer enforcer, RoleRepository roleRepository, UserRepository userRepository) {
        this.enforcer = enforcer;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    @CacheEvict(value = "resources", key = "'resources_'+#userId")
    public void addUserRoles(long userId, List<Long> roleIds) {

        List<String> rolesForUser = enforcer.getRolesForUser(String.valueOf(userId));

        List<Role> roles = roleRepository.findByIds(roleIds.stream().filter(roleId -> Objects.nonNull(roleId) && !rolesForUser.contains(String.valueOf(roleId))).toList()).stream().filter(Objects::nonNull).toList();

        User user = userRepository.findById(userId);

        if (Objects.isNull(user) || roles.isEmpty()) {
            throw new ValidationException("the user or role does not exist!");
        }

        boolean enforcerFlag = enforcer.addGroupingPolicies(roles.stream().map(role -> Arrays.asList(user.username(), role.name())).toList());

        if (!enforcerFlag) {
            throw new ValidationException("failed to add a role!");
        }
    }

    @Override
    @CacheEvict(value = "resources", key = "'resources_'+#userId")
    public void deleteUserRoles(long userId, List<Long> roleIds) {
        List<Role> roles = roleRepository.findByIds(roleIds);
        User user = userRepository.findById(userId);
        if (Objects.isNull(user)) {
            throw new ValidationException("user does not exist!");
        } else if (roles.isEmpty()) {
            return;
        }
        List<String> rolesForUser = enforcer.getRolesForUser(user.username());
        roles.stream().filter(role -> Objects.nonNull(role) && rolesForUser.contains(role.name())).forEach(role -> enforcer.deleteRoleForUser(user.username(), role.name()));
    }

    @Override
    public void updateUserRoles(long userId, UserRoleInput params) {

        User user = userRepository.findById(userId);
        if (user == null) {
            return;
        }
        userRepository.update(UserDraft.$.produce(draft -> {
            draft.setId(userId);
            if (!Objects.isNull(params.getEmail())) {
                draft.setEmail(params.getEmail());
            }
            if (!Objects.isNull(params.getPhone())) {
                draft.setPhone(params.getPhone());
            }
            if (!Objects.isNull(params.getAvatar())) {
                draft.setAvatar(params.getAvatar());
            }
        }));
        List<String> oldRoles = enforcer.getRolesForUser(user.username());

        List<String> newRoles = new ArrayList<>();
        if (params.getRoles() != null) {
            newRoles.addAll(roleRepository.findByIds(new HashSet<>(params.getRoles())).stream().map(Role::name).toList());
        }
        enforcer.updatePermissionForUser(user.username(), oldRoles, newRoles);

    }

    @Override
    public List<RoleVo> listUserRole(User user) {
        return roleRepository.listByNames(enforcer.getRolesForUser(user.username())).stream().map(RoleVo::new).toList();
    }
}
