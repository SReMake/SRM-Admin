package com.sreMake.repository.system.impl;

import com.sreMake.model.Tables;
import com.sreMake.model.system.CasbinRule;
import com.sreMake.repository.system.CasbinRuleRepository;
import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
import org.babyfish.jimmer.sql.JSqlClient;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class CasbinRuleRepositoryImpl extends AbstractJavaRepository<CasbinRule, Integer> implements CasbinRuleRepository {

    public CasbinRuleRepositoryImpl(JSqlClient sql) {
        super(sql);
    }

    @Override
    public long countMatches(String router) {
        return this.sql.createQuery(Tables.CASBIN_RULE_TABLE).where(Tables.CASBIN_RULE_TABLE.v1().eq(router)).select(Tables.CASBIN_RULE_TABLE.count()).fetchOptional().orElse(0L);
    }

    @Override
    public List<String> listMatchesRole(String... roles) {
        return this.sql.createQuery(Tables.CASBIN_RULE_TABLE).where(Tables.CASBIN_RULE_TABLE.v0().in(Arrays.stream(roles).toList())).select(Tables.CASBIN_RULE_TABLE.v1()).fetchOptional().stream().toList();
    }
}
