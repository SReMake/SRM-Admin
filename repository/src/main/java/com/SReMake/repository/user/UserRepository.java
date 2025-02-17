package com.SReMake.repository.user;


import com.SReMake.model.user.User;
import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
import org.babyfish.jimmer.sql.JSqlClient;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends AbstractJavaRepository<User, Long> {
    public UserRepository(JSqlClient sql) {
        super(sql);
    }
}
