package com.SReMake.user.service.impl;

import com.SReMake.common.exception.can.RoleException;
import com.SReMake.common.exception.can.ValidationException;
import com.SReMake.model.system.Role;
import com.SReMake.model.user.User;
import com.SReMake.repository.user.RoleRepository;
import com.SReMake.repository.user.UserRepository;
import com.SReMake.user.service.UserRoleService;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
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
    public void addUserRoles(long userId, List<Long> roleIds) {

        List<Role> roles = roleRepository.findByIds(
                        roleIds.stream()
                                .filter(Objects::nonNull)
                                .toList())
                .stream()
                .filter(Objects::nonNull).toList();

        User user = userRepository.findById(userId);

        if (Objects.isNull(user) || roles.isEmpty()) {
            throw new ValidationException("the user or role does not exist!");
        }

        boolean enforcerFlag = enforcer.addGroupingPolicies(roles.stream().map(role ->
                Arrays.asList(
                        String.valueOf(user.id()),
                        String.valueOf(role.id())
                )
        ).toList());

        if (!enforcerFlag) {
            throw new RoleException("failed to add a role!");
        }
    }

    @Override
    public void deleteUserRoles(long userId, List<Long> roleId) {

    }

    @Override
    public List<Role> listUserRole(User user) {
        return List.of();
    }
}
