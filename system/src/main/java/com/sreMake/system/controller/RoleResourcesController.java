package com.sreMake.system.controller;

import com.sreMake.common.result.ResponseResult;
import com.sreMake.system.service.RoleResourcesService;
import org.babyfish.jimmer.client.EnableImplicitApi;
import org.babyfish.jimmer.client.meta.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roleResources")
@Api
@EnableImplicitApi
public class RoleResourcesController {
    private final RoleResourcesService roleResourcesService;

    public RoleResourcesController(RoleResourcesService roleResourcesService) {
        this.roleResourcesService = roleResourcesService;
    }

    /**
     * 授权角色资源
     */
    @PutMapping("/grant/{roleId}")
    public ResponseResult<String> grantRoleResources(@PathVariable long roleId, @RequestBody List<Long> resourcesIds) {
        roleResourcesService.grantRoleResources(roleId, resourcesIds);
        return ResponseResult.success("OK");
    }

    /**
     * 撤销角色资源授权
     */
    @DeleteMapping("/revoke/{roleId}")
    public ResponseResult<String> revokeRoleResources(@PathVariable long roleId, @RequestBody List<Long> resourcesIds) {
        roleResourcesService.revokeRoleResources(roleId, resourcesIds);
        return ResponseResult.success("OK");
    }
}
