package com.SReMake.user.service.impl;

import com.SReMake.common.exception.can.RoleException;
import com.SReMake.common.exception.can.ValidationException;
import com.SReMake.model.system.Role;
import com.SReMake.model.user.User;
import com.SReMake.repository.system.CasbinRuleRepository;
import com.SReMake.repository.user.RoleRepository;
import com.SReMake.repository.user.UserRepository;
import com.SReMake.user.service.UserRoleService;
import com.SReMake.user.vo.RoleVo;
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
    private final CasbinRuleRepository ruleRepository;

    public UserRoleServiceImpl(Enforcer enforcer, RoleRepository roleRepository, UserRepository userRepository, CasbinRuleRepository ruleRepository) {
        this.enforcer = enforcer;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.ruleRepository = ruleRepository;
    }

    @Override
    public void addUserRoles(long userId, List<Long> roleIds) {

        List<String> rolesForUser = enforcer.getRolesForUser(String.valueOf(userId));

        List<Role> roles = roleRepository.findByIds(
                        roleIds.stream()
                                .filter(roleId -> Objects.nonNull(roleId) && !rolesForUser.contains(String.valueOf(roleId)))
                                .toList())
                .stream()
                .filter(Objects::nonNull).toList();

        User user = userRepository.findById(userId);

        if (Objects.isNull(user) || roles.isEmpty()) {
            throw new ValidationException("the user or role does not exist!");
        }

        boolean enforcerFlag = enforcer.addGroupingPolicies(roles.stream().map(role ->
                Arrays.asList(
                        user.username(),
                        role.name()
                )
        ).toList());

        if (!enforcerFlag) {
            throw new RoleException("failed to add a role!");
        }
    }

    @Override
    public void deleteUserRoles(long userId, List<Long> roleIds) {
        List<Role> roles = roleRepository.findByIds(roleIds);
        User user = userRepository.findById(userId);
        if (Objects.isNull(user) || roles.isEmpty()) {
            throw new ValidationException("the user or role does not exist!");
        }
        List<String> rolesForUser = enforcer.getRolesForUser(user.username());
        roles.stream()
                .filter(role -> Objects.nonNull(role) && rolesForUser.contains(role.name()))
                .forEach(role -> {
                    enforcer.deleteRoleForUser(user.username(), role.name());
                });
    }

    @Override
    public List<RoleVo> listUserRole(User user) {
        return roleRepository.findByNams(enforcer.getRolesForUser(user.username())).stream().map(RoleVo::new).toList();
    }
}
