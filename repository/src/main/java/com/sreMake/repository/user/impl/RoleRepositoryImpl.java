package com.sreMake.repository.user.impl;

import com.sreMake.model.Fetchers;
import com.sreMake.model.Tables;
import com.sreMake.model.user.Role;
import com.sreMake.model.user.dto.RoleSearchInput;
import com.sreMake.repository.user.RoleRepository;
import org.babyfish.jimmer.Page;
import org.babyfish.jimmer.spring.repo.PageParam;
import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.ast.LikeMode;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class RoleRepositoryImpl extends AbstractJavaRepository<Role, Long> implements RoleRepository {
    public RoleRepositoryImpl(JSqlClient sql) {
        super(sql);
    }

    @Override
    public Page<Role> findPage(PageParam pageParam, @NotNull RoleSearchInput params) {
        return this.sql.createQuery(Tables.ROLE_TABLE).where(
                        Tables.ROLE_TABLE.name().likeIf(params.getName(), LikeMode.ANYWHERE)
                ).orderBy(Tables.ROLE_TABLE.id().desc())
                .select(Tables.ROLE_TABLE.fetch(
                        Fetchers.ROLE_FETCHER.allScalarFields()
                                .createBy(Fetchers.USER_FETCHER.username())
                                .updateBy(Fetchers.USER_FETCHER.username())
                ))
                .fetchPage(pageParam.getIndex(), pageParam.getSize());

    }

    @Override
    public List<Role> listByNames(Collection<String> names) {

        return this.sql.createQuery(Tables.ROLE_TABLE).where(
                Tables.ROLE_TABLE.name().in(names)
        ).select(Tables.ROLE_TABLE).fetchOptional().stream().toList();
    }

    @Override
    public Role findByName(String name) {
        return this.sql.createQuery(Tables.ROLE_TABLE).where(
                Tables.ROLE_TABLE.name().eq(name)
        ).select(Tables.ROLE_TABLE).fetchFirst();
    }
}
