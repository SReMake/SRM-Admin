package com.sreMake.user.controller;

import com.sreMake.common.result.ResponseResult;
import com.sreMake.model.security.CustomUserDetails;
import com.sreMake.model.user.dto.UserRoleInput;
import com.sreMake.user.service.UserRoleService;
import com.sreMake.user.vo.RoleVo;
import org.babyfish.jimmer.client.EnableImplicitApi;
import org.babyfish.jimmer.client.meta.Api;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/api/v1/userRole")
@EnableImplicitApi
public class UserRoleController {
    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    /**
     * 给用户赋予角色
     */
    @PostMapping("/{userId}")
    public ResponseResult<String> addUserRoles(@PathVariable long userId, @RequestBody List<Long> roleIds) {
        userRoleService.addUserRoles(userId, roleIds);
        return ResponseResult.success("OK");
    }

    /**
     * 删除用户的角色
     */
    @DeleteMapping("/{userId}")
    public ResponseResult<String> deleteUserRoles(@PathVariable long userId, @RequestBody List<Long> roleIds) {
        userRoleService.deleteUserRoles(userId, roleIds);
        return ResponseResult.success("OK");
    }

    /**
     * 更新修改用户角色信息
     */
    @PutMapping("/{userId}")
    public ResponseResult<String> updateUserRoles(@PathVariable long userId, @RequestBody UserRoleInput userRoleInput) {
        userRoleService.updateUserRoles(userId, userRoleInput);
        return ResponseResult.success("OK");
    }

    /**
     * 查看当前用户角色
     */
    @GetMapping()
//  由于jimmer 无法兼容修改为该方法  public ResponseResult<List<RoleVo>> listUserRole(@AuthenticationPrincipal CustomUserDetails userDetails) {
    public ResponseResult<List<RoleVo>> listUserRole() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseResult.success(userRoleService.listUserRole(userDetails.getUser()));
    }

}
