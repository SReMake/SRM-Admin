package com.SReMake.repository.user.impl;


import com.SReMake.model.Tables;
import com.SReMake.model.user.User;
import com.SReMake.model.user.dto.UserSearchInput;
import com.SReMake.repository.user.UserRepository;
import org.babyfish.jimmer.Input;
import org.babyfish.jimmer.Page;
import org.babyfish.jimmer.spring.repo.PageParam;
import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.ast.LikeMode;
import org.babyfish.jimmer.sql.ast.mutation.AssociatedSaveMode;
import org.babyfish.jimmer.sql.ast.mutation.SimpleSaveResult;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends AbstractJavaRepository<User, Long> implements UserRepository {


    public UserRepositoryImpl(JSqlClient sql) {
        super(sql);
    }

    @Override
    public Page<User> findPage(PageParam pageParam, UserSearchInput params) {
        return this.sql.createQuery(Tables.USER_TABLE).where(
                        Tables.USER_TABLE.id().eqIf(params.getId()),
                        Tables.USER_TABLE.username().likeIf(params.getUsername(), LikeMode.ANYWHERE),
                        Tables.USER_TABLE.status().eqIf(params.getStatus()),
                        Tables.USER_TABLE.phone().likeIf(params.getPhone(), LikeMode.ANYWHERE),
                        Tables.USER_TABLE.createAt().betweenIf(params.getCreateAtStart(), params.getCreateAtEnd()),
                        Tables.USER_TABLE.updateAt().betweenIf(params.getUpdateAtStart(), params.getUpdateAtEnd())

                ).orderBy(Tables.USER_TABLE.id().desc())
                .select(Tables.USER_TABLE)
                .fetchPage(pageParam.getIndex(), pageParam.getSize());
    }

    @Override
    public User findByUsername(String username) {
        return this.sql.createQuery(Tables.USER_TABLE).where(
                        Tables.USER_TABLE.username().eq(username),
                        Tables.USER_TABLE.status().eq(User.Status.NORMAL)
                ).select(Tables.USER_TABLE)
                .fetchOne();
    }
}
