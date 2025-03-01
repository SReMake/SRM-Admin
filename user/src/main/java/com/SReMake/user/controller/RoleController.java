package com.SReMake.user.controller;

import com.SReMake.common.result.ResponseResult;
import com.SReMake.common.result.ResponseResultPage;
import com.SReMake.model.user.Role;
import com.SReMake.model.user.dto.RoleSearchInput;
import com.SReMake.model.user.dto.UpdateRoleInput;
import com.SReMake.security.spring.CustomUserDetails;
import com.SReMake.user.service.RoleService;
import org.babyfish.jimmer.client.EnableImplicitApi;
import org.babyfish.jimmer.spring.repo.PageParam;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/role")
@EnableImplicitApi
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 添加角色
     */
    @PostMapping()
    public ResponseResult<String> addRole(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody UpdateRoleInput role) {
        roleService.addRole(userDetails.getUser(), role);
        return ResponseResult.success("OK");
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/{id}")
    public ResponseResult<String> deleteRole(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable long id) {
        roleService.deleteRole(id);
        return ResponseResult.success("OK");
    }

    /**
     * 更新角色
     */
    @PutMapping("/{id}")
    public ResponseResult<String> updateRole(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable long id, @RequestBody UpdateRoleInput role) {
        roleService.updateRole(id, userDetails.getUser(), role);
        return ResponseResult.success("OK");
    }

    /**
     * 获取角色列表
     */
    @GetMapping("/list")
    public ResponseResultPage<Role> listRole(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(required = false) RoleSearchInput role) {
        PageParam pageParam = PageParam.byNo(page, size);
        return ResponseResultPage.success(roleService.listRole(pageParam, role), pageParam);
    }
}
