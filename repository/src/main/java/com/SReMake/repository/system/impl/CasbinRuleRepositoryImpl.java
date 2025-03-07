package com.SReMake.repository.system.impl;

//import com.SReMake.model.Tables;
//import com.SReMake.model.system.CasbinRule;
//import com.SReMake.repository.system.CasbinRuleRepository;
//import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
//import org.babyfish.jimmer.sql.JSqlClient;
//import org.springframework.stereotype.Repository;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Repository
//public class CasbinRuleRepositoryImpl extends AbstractJavaRepository<CasbinRule, Integer> implements CasbinRuleRepository {
//
//    public CasbinRuleRepositoryImpl(JSqlClient sql) {
//        super(sql);
//    }
//
//    @Override
//    public long countMatches(String router) {
//        return this.sql.createQuery(Tables.CASBIN_RULE_TABLE).where(Tables.CASBIN_RULE_TABLE.v1().eq(router)).select(Tables.CASBIN_RULE_TABLE.count()).fetchOptional().orElse(0L);
//    }
//
//    @Override
//    public List<String> listMatchesRole(String... roles) {
//        return this.sql.createQuery(Tables.CASBIN_RULE_TABLE).where(Tables.CASBIN_RULE_TABLE.v0().in(Arrays.stream(roles).toList())).select(Tables.CASBIN_RULE_TABLE.v1()).fetchOptional().stream().toList();
//    }
//}
