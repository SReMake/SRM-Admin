package com.sreMake.user.controller;

import com.sreMake.common.result.ResponseResult;
import com.sreMake.model.security.CustomUserDetails;
import com.sreMake.user.service.UserRoleService;
import com.sreMake.user.vo.RoleVo;
import org.babyfish.jimmer.client.ApiIgnore;
import org.babyfish.jimmer.client.EnableImplicitApi;
import org.babyfish.jimmer.client.meta.Api;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
     * 查看当前用户角色
     */
    @GetMapping()
    public ResponseResult<List<RoleVo>> listUserRole(@ApiIgnore @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseResult.success(userRoleService.listUserRole(userDetails.getUser()));
    }

}
