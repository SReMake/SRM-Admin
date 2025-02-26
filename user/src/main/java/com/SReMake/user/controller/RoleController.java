package com.SReMake.user.controller;

import com.SReMake.common.result.ResponseResult;
import com.SReMake.common.result.ResponseResultPage;
import com.SReMake.model.system.Role;
import com.SReMake.model.system.dto.RoleSearchInput;
import com.SReMake.model.system.dto.UpdateRoleInput;
import com.SReMake.user.service.RoleService;
import org.babyfish.jimmer.client.EnableImplicitApi;
import org.babyfish.jimmer.spring.repo.PageParam;
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
    public ResponseResult<String> addRole(@RequestBody UpdateRoleInput role) {
        roleService.addRole(role);
        return ResponseResult.success("OK");
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/{id}")
    public ResponseResult<String> deleteRole(@PathVariable long id) {
        roleService.deleteRole(id);
        return ResponseResult.success("OK");
    }

    /**
     * 更新角色
     */
    @PutMapping("/{id}")
    public ResponseResult<String> updateRole(@PathVariable long id, @RequestBody UpdateRoleInput role) {
        roleService.updateRole(id, role);
        return ResponseResult.success("OK");
    }

    /**
     * 获取角色列表
     */
    @GetMapping("/list")
    public ResponseResultPage<Role> listRole(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestBody RoleSearchInput role) {
        PageParam pageParam = PageParam.byNo(page, size);
        return ResponseResultPage.success(roleService.listRole(pageParam, role), pageParam);
    }
}
