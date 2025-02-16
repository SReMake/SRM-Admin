package com.rerubbish.repository.user;

import com.rerubbish.model.user.Role;
import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
import org.babyfish.jimmer.sql.JSqlClient;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepository extends AbstractJavaRepository<Role, Long> {
    public RoleRepository(JSqlClient sql) {
        super(sql);
    }
}
