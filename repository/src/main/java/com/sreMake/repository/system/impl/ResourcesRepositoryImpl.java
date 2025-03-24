package com.sreMake.repository.system.impl;

import com.sreMake.model.Tables;
import com.sreMake.model.system.Resources;
import com.sreMake.repository.system.ResourcesRepository;
import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
import org.babyfish.jimmer.sql.JSqlClient;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class ResourcesRepositoryImpl extends AbstractJavaRepository<Resources, Long> implements ResourcesRepository {
    public ResourcesRepositoryImpl(JSqlClient sql) {
        super(sql);
    }


    @Override
    public List<Resources> listByRouters(String... routers) {
        return this.sql.createQuery(Tables.RESOURCES_TABLE)
                .where(Tables.RESOURCES_TABLE.resources().in(Arrays.stream(routers).toList()))
                .select(Tables.RESOURCES_TABLE)
                .fetchOptional().stream().toList();
    }

    @Override
    public List<Resources> listParentId(Long... parentIds) {
        return this.sql.createQuery(Tables.RESOURCES_TABLE)
                .where(Tables.RESOURCES_TABLE.id().in(Arrays.stream(parentIds).toList()))
                .select(Tables.RESOURCES_TABLE)
                .fetchOptional().stream().toList();
    }
}
