package com.rerubbish.user;

import com.rerubbish.BasicEntity;
import org.babyfish.jimmer.sql.Entity;
import org.babyfish.jimmer.sql.ManyToOne;

@Entity
public interface Role extends BasicEntity {

    @ManyToOne()
    User user();

    long role();
}
