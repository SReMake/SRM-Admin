package com.sreMake.user.controller;

import com.sreMake.common.result.ResponseResult;
import com.sreMake.common.result.ResponseResultPage;
import com.sreMake.model.user.dto.UpdateUserInput;
import com.sreMake.model.user.dto.UserInput;
import com.sreMake.model.user.dto.UserSearchInput;
import com.sreMake.user.service.UserService;
import com.sreMake.user.vo.UserVo;
import org.babyfish.jimmer.client.EnableImplicitApi;
import org.babyfish.jimmer.client.meta.Api;
import org.babyfish.jimmer.spring.repo.PageParam;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/api/v1/user")
@EnableImplicitApi
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 添加用户
     */
    @PostMapping("")
    public ResponseResult<String> addUser(@RequestBody UserInput user) {
        userService.addUser(user);
        return ResponseResult.success("OK");
    }

    /**
     * 查看用户列表
     */
    @GetMapping("/list")
    public ResponseResultPage<UserVo> listUser(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(required = false) UserSearchInput params) {
        PageParam pageParam = PageParam.byNo(page, size);
        return ResponseResultPage.success(userService.listUser(pageParam, params), pageParam);
    }

    /**
     * 更新用户信息
     */
    @PutMapping("/{id}")
    public ResponseResult<String> updateUser(@PathVariable long id, @RequestBody UpdateUserInput user) {
        userService.updateUser(id, user);
        return ResponseResult.success("OK");
    }

    /**
     * 禁用账户
     */
    @DeleteMapping("/{id}")
    public ResponseResult<String> disableUser(@PathVariable long id) {
        userService.disableUser(id);
        return ResponseResult.success("OK");
    }

    /**
     * 启用账户
     */
    @PutMapping("/{id}/enable")
    public ResponseResult<String> enableUser(@PathVariable long id) {
        userService.enableUser(id);
        return ResponseResult.success("OK");
    }
}
