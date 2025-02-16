package com.rerubbish.repository.system;

import org.babyfish.jimmer.sql.JSqlClient;
import org.springframework.stereotype.Repository;

@Repository
public class CasbinRuleRepository {
    private final JSqlClient sql;

    public CasbinRuleRepository(JSqlClient sql) {
        this.sql = sql;
    }
}
