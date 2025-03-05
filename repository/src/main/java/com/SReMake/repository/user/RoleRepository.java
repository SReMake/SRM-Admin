package com.SReMake.repository.user;

import com.SReMake.model.user.Role;
import com.SReMake.model.user.dto.RoleSearchInput;
import org.babyfish.jimmer.Page;
import org.babyfish.jimmer.spring.repo.JavaRepository;
import org.babyfish.jimmer.spring.repo.PageParam;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public interface RoleRepository extends JavaRepository<Role, Long> {
    /**
     * 分页查询
     */
    Page<Role> findPage(PageParam pageParam, @NotNull RoleSearchInput params);

    /**
     * 根据角色名查询
     */
    List<Role> listByNames(Collection<String> names);
}
