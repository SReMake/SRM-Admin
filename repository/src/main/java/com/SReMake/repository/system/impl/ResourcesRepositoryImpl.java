package com.SReMake.repository.system.impl;

import com.SReMake.model.system.Resources;
import com.SReMake.repository.system.ResourcesRepository;
import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
import org.babyfish.jimmer.sql.JSqlClient;
import org.springframework.stereotype.Repository;

@Repository
public class ResourcesRepositoryImpl extends AbstractJavaRepository<Resources, Long> implements ResourcesRepository {
    public ResourcesRepositoryImpl(JSqlClient sql) {
        super(sql);
    }
}
