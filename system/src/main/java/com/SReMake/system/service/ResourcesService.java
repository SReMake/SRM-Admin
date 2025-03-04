package com.SReMake.system.service;

import com.SReMake.model.system.dto.ResourcesInput;
import com.SReMake.model.user.User;
import com.SReMake.system.vo.ResourcesVo;
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
     * 获取资源列表
     */
    List<ResourcesVo> listResources(@NotNull User user, List<String> roles);

}
