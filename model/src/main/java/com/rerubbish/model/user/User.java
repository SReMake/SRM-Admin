package com.rerubbish.model.user;

import com.rerubbish.model.BasicEntity;
import org.babyfish.jimmer.sql.Entity;
import org.babyfish.jimmer.sql.OneToMany;

import java.util.List;

@Entity
public interface User extends BasicEntity {

    String username();

    String password();

    String phone();

    String avatar();

    UserStatus status();

    @OneToMany(mappedBy = "user")
    List<Role> roles();
}

