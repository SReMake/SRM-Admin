package com.sreMake.security.casbin;

import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import org.casbin.adapter.JDBCAdapter;
import org.casbin.jcasbin.main.Enforcer;
import org.casbin.jcasbin.model.Model;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class CasbinEnforcer {
    private final Enforcer enforcer;

    @SneakyThrows
    public CasbinEnforcer(HikariDataSource dataSource) {
        JDBCAdapter adapter = new JDBCAdapter(dataSource);
//        rbac 模型
        Model model = Model.newModelFromString("""
                [request_definition]
                r = sub, router, act
                
                [policy_definition]
                p = sub, router, act
                
                [role_definition]
                g = _, _
                
                [policy_effect]
                e = some(where (p.eft == allow))
                
                [matchers]
                m = g(r.sub, p.sub) && r.router == p.router && r.act == p.act""");
        enforcer = new Enforcer(model, adapter, true);
    }

    @Bean
    public Enforcer enforcer() {
        return enforcer;
    }
}
