package com.SReMake.repository.user.impl;


import com.SReMake.model.user.User;
import com.SReMake.model.user.UserTable;
import com.SReMake.model.user.dto.UserSearchInput;
import com.SReMake.repository.user.UserRepository;
import org.babyfish.jimmer.Page;
import org.babyfish.jimmer.spring.repo.PageParam;
import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.ast.LikeMode;
import org.babyfish.jimmer.sql.ast.Predicate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends AbstractJavaRepository<User, Long> implements UserRepository {
    private final UserTable table = UserTable.$;

    public UserRepositoryImpl(JSqlClient sql) {
        super(sql);
    }

    @Override
    public Page<User> findPage(PageParam pageParam, UserSearchInput params) {
        return this.sql.createQuery(table).where(
                table.id().eqIf(params.getId()),
                table.username().likeIf(params.getUsername(), LikeMode.EXACT),
                table.status().eqIf(params.getStatus()),
                table.phone().likeIf(params.getPhone(), LikeMode.EXACT),
                table.createAt().betweenIf(params.getCreateAtStart(), params.getCreateAtEnd()),
                table.updateAt().betweenIf(params.getUpdateAtStart(), params.getUpdateAtEnd())

        ).orderBy(table.id().desc()).select(table).fetchPage(pageParam.getIndex(), pageParam.getSize());
    }
}
