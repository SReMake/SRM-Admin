package com.rerubbish.model.user;

import com.rerubbish.model.BasicEntity;
import org.babyfish.jimmer.sql.Entity;
import org.babyfish.jimmer.sql.ManyToOne;

@Entity
public interface Role extends BasicEntity {

    @ManyToOne()
    User user();

    long role();
}
