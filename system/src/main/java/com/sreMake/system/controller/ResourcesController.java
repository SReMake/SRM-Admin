package com.sreMake.system.controller;

import com.sreMake.common.result.ResponseResult;
import com.sreMake.model.security.CustomUserDetails;
import com.sreMake.model.system.dto.ResourcesInput;
import com.sreMake.system.service.ResourcesService;
import com.sreMake.system.vo.ApiVo;
import com.sreMake.system.vo.ResourcesVo;
import org.babyfish.jimmer.client.EnableImplicitApi;
import org.babyfish.jimmer.client.meta.Api;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resources")
@Api
@EnableImplicitApi
public class ResourcesController {
    private final ResourcesService resourcesService;

    public ResourcesController(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
    }

    /**
     * 添加资源
     */
    @PostMapping()
    public ResponseResult<String> addResources(@RequestBody ResourcesInput params) {
        resourcesService.addResources(params);
        return ResponseResult.success("OK");
    }

    /**
     * 删除资源
     */
    @DeleteMapping("/{id}")
    public ResponseResult<String> deleteResources(@PathVariable long id) {
        resourcesService.deleteResources(id);
        return ResponseResult.success("OK");
    }

    /**
     * 获取资源列表
     */
    @GetMapping("/list")
    public ResponseResult<List<ResourcesVo>> listResources(@AuthenticationPrincipal CustomUserDetails userDetails) {

        return ResponseResult.success(
                resourcesService.listResources(
                        userDetails.getUser(),
                        userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
                )
        );
    }
    /**
     * 获取后端框架的全部API
     * */
    @GetMapping("/apis")
    public ResponseResult<List<ApiVo>> listApis(@AuthenticationPrincipal CustomUserDetails userDetails){
        return ResponseResult.success(
                resourcesService.listApis(
                        userDetails.getUser(),
                        userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
                )
        );
    }
}
