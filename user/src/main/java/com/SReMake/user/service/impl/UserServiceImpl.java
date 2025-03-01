package com.SReMake.user.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.SReMake.model.user.User;
import com.SReMake.model.user.UserDraft;
import com.SReMake.model.user.dto.UpdateUserInput;
import com.SReMake.model.user.dto.UserInput;
import com.SReMake.model.user.dto.UserSearchInput;
import com.SReMake.repository.user.UserRepository;
import com.SReMake.repository.user.impl.UserRepositoryImpl;
import com.SReMake.user.service.UserService;
import com.SReMake.user.vo.UserVo;
import org.babyfish.jimmer.Page;
import org.babyfish.jimmer.spring.repo.PageParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user, UserInput params) {
        String BcryptPassword = BCrypt.hashpw(params.getPassword());
        params.setPassword(BcryptPassword);
        userRepository.insert(params);
    }

    @Override
    public Page<UserVo> listUser(PageParam pageParam, UserSearchInput params) {
        Page<User> result = userRepository.findPage(pageParam, params);
        return new Page<>(result.getRows().stream().map(UserVo::new).toList(), result.getTotalRowCount(), result.getTotalPageCount());
    }

    @Override
    public void updateUser(long id, User user, UpdateUserInput params) {
        userRepository.update(UserDraft.$.produce(draft -> {
            draft.setId(id);
            if (!Objects.isNull(params.getUsername())) {
                draft.setUsername(params.getUsername());
            }
            if (!Objects.isNull(params.getEmail())) {
                draft.setEmail(params.getEmail());
            }
            if (!Objects.isNull(params.getPhone())) {
                draft.setPhone(params.getPhone());
            }
            if (!Objects.isNull(params.getAvatar())) {
                draft.setAvatar(params.getAvatar());
            }
            draft.setUpdateBy(user);
        }));
    }

    @Override
    public void disableUser(long id, User user) {
        userRepository.update(UserDraft.$.produce(draft -> {
            draft.setId(id);
            draft.setStatus(User.Status.DISABLE);
            draft.setUpdateBy(user);
        }));
    }

    @Override
    public void enableUser(long id, User user) {
        userRepository.update(UserDraft.$.produce(draft -> {
            draft.setId(id);
            draft.setStatus(User.Status.NORMAL);
            draft.setUpdateBy(user);
        }));
    }
}
