package com.sreMake.system.service;

import com.sreMake.model.system.dto.ResourcesInput;
import com.sreMake.model.user.User;
import com.sreMake.system.vo.ApiVo;
import com.sreMake.system.vo.ResourcesVo;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ResourcesService {
    /**
     * 添加资源
     */
    void addResources(ResourcesInput params);

    /**
     * 删除资源
     */
    void deleteResources(long id);

    /**
     * 修改资源
     */
    void updateResources(long id, ResourcesInput params);

    /**
     * 获取资源列表
     */
    List<ResourcesVo> listResources(@NotNull User user, List<String> roles);

    /**
     * 获取后端框架的全部API
     */
    List<ApiVo> listApis(@NotNull User user, List<String> roles);

}
