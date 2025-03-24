package com.sreMake.repository.system;

import com.sreMake.model.system.Resources;
import org.babyfish.jimmer.spring.repo.JavaRepository;

import java.util.List;

public interface ResourcesRepository extends JavaRepository<Resources, Long> {

    /**
     * 通过路由路径获取Resources
     */
    List<Resources> listByRouters(String... routers);

    /**
     * parent 获取全部相关的父级
     */
    List<Resources> listParentId(Long... parentIds);
}
