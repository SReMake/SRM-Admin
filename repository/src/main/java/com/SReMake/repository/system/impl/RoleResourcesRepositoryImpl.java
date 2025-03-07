package com.SReMake.repository.system.impl;

//import com.SReMake.model.Tables;
//import com.SReMake.model.system.Resources;
//import com.SReMake.model.system.RoleResources;
//import com.SReMake.repository.system.RoleResourcesRepository;
//import org.babyfish.jimmer.spring.repo.support.AbstractJavaRepository;
//import org.babyfish.jimmer.sql.JSqlClient;
//import org.springframework.stereotype.Repository;
//
//import java.util.Collection;
//import java.util.List;
//
//@Repository
//public class RoleResourcesRepositoryImpl extends AbstractJavaRepository<RoleResources, Long> implements RoleResourcesRepository {
//    public RoleResourcesRepositoryImpl(JSqlClient sql) {
//        super(sql);
//    }
//
//
//    @Override
//    public List<Resources> listByRole(Collection<Long> roleIds) {
//
//        return this.sql.createQuery(Tables.ROLE_RESOURCES_TABLE)
//                .where(Tables.ROLE_RESOURCES_TABLE.roleId().in(roleIds))
//                .select(Tables.ROLE_RESOURCES_TABLE.resources())
//                .fetchOptional().stream().toList();
//    }
//
//    @Override
//    public void deleteByRoleIdAndResourceId(Long roleId, Collection<Long> resourceId) {
//        this.sql.createDelete(Tables.ROLE_RESOURCES_TABLE).where(Tables.ROLE_RESOURCES_TABLE.roleId().eq(roleId), Tables.ROLE_RESOURCES_TABLE.resourcesId().in(resourceId)).execute();
//    }
//}
