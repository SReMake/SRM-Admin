package com.SReMake.repository.user.impl;

import com.SReMake.model.Tables;
import com.SReMake.model.user.Role;
import com.SReMake.model.user.dto.RoleSearchInput;
import com.SReMake.repository.user.RoleRepository;
import org.babyfish.jimmer.Page;
import org.babyfish.jimmer.spring.repo.PageParam;
import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.ast.LikeMode;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Repository
public class RoleRepositoryImpl extends AbstractJavaRepository<Role, Long> implements RoleRepository {
    public RoleRepositoryImpl(JSqlClient sql) {
        super(sql);
    }

    @Override
    public Page<Role> findPage(PageParam pageParam, RoleSearchInput params) {
        if (Objects.isNull(params)) {
            return this.findPage(pageParam);
        } else {
            return this.sql.createQuery(Tables.ROLE_TABLE).where(
                            Tables.ROLE_TABLE.name().likeIf(params.getName(), LikeMode.ANYWHERE)
                    ).orderBy(Tables.ROLE_TABLE.id().desc())
                    .select(Tables.ROLE_TABLE)
                    .fetchPage(pageParam.getIndex(), pageParam.getSize());
        }

    }

    @Override
    public List<Role> findByNams(Collection<String> names) {

        return this.sql.createQuery(Tables.ROLE_TABLE).where(
                Tables.ROLE_TABLE.name().in(names)
        ).select(Tables.ROLE_TABLE).fetchOptional().stream().toList();
    }
}
