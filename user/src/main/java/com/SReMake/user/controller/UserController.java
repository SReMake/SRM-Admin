package com.SReMake.user.controller;

import com.SReMake.common.result.ResponseResult;
import com.SReMake.common.result.ResponseResultPage;
import com.SReMake.model.user.dto.UpdateUserInput;
import com.SReMake.model.user.dto.UserInput;
import com.SReMake.model.user.dto.UserSearchInput;
import com.SReMake.security.spring.CustomUserDetails;
import com.SReMake.user.service.UserService;
import com.SReMake.user.vo.UserVo;
import org.babyfish.jimmer.client.EnableImplicitApi;
import org.babyfish.jimmer.client.meta.Api;
import org.babyfish.jimmer.spring.repo.PageParam;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseResult<String> addUser(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody UserInput user) {
        userService.addUser(userDetails.getUser(), user);
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
    public ResponseResult<String> updateUser(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable long id, @RequestBody UpdateUserInput user) {
        userService.updateUser(id, userDetails.getUser(), user);
        return ResponseResult.success("OK");
    }

    /**
     * 禁用账户
     */
    @DeleteMapping("/{id}")
    public ResponseResult<String> disableUser(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable long id) {
        userService.disableUser(id, userDetails.getUser());
        return ResponseResult.success("OK");
    }

    /**
     * 启用账户
     */
    @PutMapping("/{id}/enable")
    public ResponseResult<String> enableUser(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable long id) {
        userService.enableUser(id, userDetails.getUser());
        return ResponseResult.success("OK");
    }
}
