package com.SReMake.system.service;

import com.SReMake.model.system.dto.ResourcesInput;

public interface ResourcesService {
    /**
     * 添加资源
     */
    void addResources(ResourcesInput params);

    /**
     * 删除资源
     */
    void deleteResources(long id);
}
