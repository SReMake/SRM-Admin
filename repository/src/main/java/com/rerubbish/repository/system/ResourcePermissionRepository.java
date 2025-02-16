package com.rerubbish.repository.system;

import com.rerubbish.model.system.ResourcePermission;
import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
import org.babyfish.jimmer.sql.JSqlClient;
import org.springframework.stereotype.Repository;

@Repository
public class ResourcePermissionRepository extends AbstractJavaRepository<ResourcePermission, Long> {
    public ResourcePermissionRepository(JSqlClient sql) {
        super(sql);
    }
}
