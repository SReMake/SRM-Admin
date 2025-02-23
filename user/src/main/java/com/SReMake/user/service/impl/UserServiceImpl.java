package com.SReMake.user.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
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
    public void addUser(UserInput user) {
        String salt = RandomUtil.randomString(16);
        user.setSalt(salt);
        // 对密码进行加盐后哈希处理
        String hashedPassword = DigestUtil.md5Hex(user.getPassword() + salt);
        user.setPassword(hashedPassword);
        userRepository.insert(user);
    }

    @Override
    public Page<UserVo> listUser(PageParam pageParam, UserSearchInput params) {
        Page<User> result = userRepository.findPage(pageParam, params);
        return new Page<>(result.getRows().stream().map(UserVo::new).toList(), result.getTotalRowCount(), result.getTotalPageCount());
    }

    @Override
    public void updateUser(long id, UpdateUserInput user) {
        userRepository.update(UserDraft.$.produce(draft -> {
            draft.setId(id);
            if (!Objects.isNull(user.getUsername())) {
                draft.setUsername(user.getUsername());
            }
            if (!Objects.isNull(user.getPhone())) {
                draft.setPhone(user.getPhone());
            }
            if (!Objects.isNull(user.getAvatar())) {
                draft.setAvatar(user.getAvatar());
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
