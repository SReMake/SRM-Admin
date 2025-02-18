package com.SReMake.user;

import com.SReMake.common.result.ResponseResult;
import com.SReMake.model.user.User;
import com.SReMake.repository.user.UserRepository;
import org.babyfish.jimmer.client.meta.Api;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    /**
//     * 添加用户
//     */
//    @PostMapping("/")
//    public ResponseResult<String> addUser(User user) {
//        userRepository.save(user);
//        return ResponseResult.success("OK");
//    }

    /**
     * 查看用户列表
     */
    @GetMapping("/list")
    public ResponseResult<List<User>> listUser() {
        return ResponseResult.success(userRepository.findAll());
    }

//    /**
//     * 更新用户信息
//     */
//    @PutMapping("/")
//    public ResponseResult<User> updateUser(User user) {
//        userRepository.save(user);
//        return ResponseResult.success(user);
//    }
}
