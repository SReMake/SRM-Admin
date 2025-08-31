package com.sreMake.user.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.sreMake.model.system.RoleResourcesDraft;
import com.sreMake.model.user.Role;
import com.sreMake.model.user.User;
import com.sreMake.model.user.UserDraft;
import com.sreMake.model.user.dto.UpdateUserInput;
import com.sreMake.model.user.dto.UserInput;
import com.sreMake.model.user.dto.UserSearchInput;
import com.sreMake.repository.system.RoleResourcesRepository;
import com.sreMake.repository.user.RoleRepository;
import com.sreMake.repository.user.UserRepository;
import com.sreMake.repository.user.impl.UserRepositoryImpl;
import com.sreMake.user.service.UserService;
import com.sreMake.user.vo.UserVo;
import org.babyfish.jimmer.Page;
import org.babyfish.jimmer.spring.repo.PageParam;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final Enforcer enforcer;

    public UserServiceImpl(UserRepositoryImpl userRepository, RoleRepository roleRepository, Enforcer enforcer) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.enforcer = enforcer;
    }

    @Override
    public void addUser(UserInput params) {
        String BcryptPassword = BCrypt.hashpw(params.getPassword());
        params.setPassword(BcryptPassword);
        userRepository.insert(params);
    }

    @Override
    public Page<UserVo> listUser(PageParam pageParam, UserSearchInput params) {
        Page<User> result = userRepository.findPage(pageParam, params);
        return new Page<>(result.getRows().stream().map(user -> {
            List<String> roleNames = enforcer.getRolesForUser(user.username());
            if (!roleNames.isEmpty()) {
                return new UserVo(user, roleRepository.listByNames(roleNames));
            } else {
                return new UserVo(user, null);
            }
        }).toList(), result.getTotalRowCount(), result.getTotalPageCount());
    }


    @Override
    public void updateUser(long id, UpdateUserInput params) {
        User user = userRepository.findById(id);

        if (params.getRoleIds() != null && user != null) {
            List<String> newRoles = roleRepository.findByIds(new HashSet<>(params.getRoleIds())).stream().map(Role::name).toList();
            List<String> oldRoles = enforcer.getRolesForUser(user.username());
            enforcer.updatePermissionForUser(user.username(), oldRoles, newRoles);
        }

        userRepository.update(UserDraft.$.produce(draft -> {
            draft.setId(id);
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
    }

    @Override
    public void disableUser(long id) {
        userRepository.update(UserDraft.$.produce(draft -> {
            draft.setId(id);
            draft.setStatus(User.Status.DISABLE);
        }));
    }

    @Override
    public void enableUser(long id) {
        userRepository.update(UserDraft.$.produce(draft -> {
            draft.setId(id);
            draft.setStatus(User.Status.NORMAL);
        }));
    }
}
