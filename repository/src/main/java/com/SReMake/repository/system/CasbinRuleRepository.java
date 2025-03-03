package com.SReMake.repository.system;

import com.SReMake.model.system.CasbinRule;
import org.babyfish.jimmer.spring.repo.JavaRepository;

public interface CasbinRuleRepository extends JavaRepository<CasbinRule, Integer> {
    /**
     * 查询casbin中有多少匹配到的
     */
    int countMatches(String router);
}
