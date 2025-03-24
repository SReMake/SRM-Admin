package com.sreMake.repository.system;

import com.sreMake.model.system.CasbinRule;
import org.babyfish.jimmer.spring.repo.JavaRepository;

import java.util.List;

public interface CasbinRuleRepository extends JavaRepository<CasbinRule, Integer> {
    /**
     * 查询casbin中有多少匹配到的
     */
    long countMatches(String router);

    /**
     * 根据角色查询资源
     */
    List<String> listMatchesRole(String... roles);
}
