package com.SReMake.repository.user;

import com.SReMake.model.system.Role;
import com.SReMake.model.system.dto.RoleSearchInput;
import org.babyfish.jimmer.Page;
import org.babyfish.jimmer.spring.repo.JavaRepository;
import org.babyfish.jimmer.spring.repo.PageParam;

public interface RoleRepository extends JavaRepository<Role, Long> {
    /**
     * 分页查询
     */
    Page<Role> findPage(PageParam pageParam, RoleSearchInput params);


}
