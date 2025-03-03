package com.SReMake.repository.system.impl;

import com.SReMake.model.system.CasbinRule;
import com.SReMake.repository.system.CasbinRuleRepository;
import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
import org.babyfish.jimmer.sql.JSqlClient;
import org.springframework.stereotype.Repository;

@Repository
public class CasbinRuleRepositoryImpl extends AbstractJavaRepository<CasbinRule, Integer> implements CasbinRuleRepository {

    public CasbinRuleRepositoryImpl(JSqlClient sql) {
        super(sql);
    }

    @Override
    public int countMatches(String router) {
        return 0;
    }
}
