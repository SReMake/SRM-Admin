package com.sreMake.model.system;

import com.sreMake.model.BaseEntity;
import org.babyfish.jimmer.sql.*;
import org.jetbrains.annotations.Nullable;

@Entity
@Table(name = "srm_resources")
public interface Resources extends BaseEntity {
    /**
     * 前端页面文件地址或者后端api接口
     */
    @Nullable
    String resources();

    /**
     * 前端路由地址
     */
    @Nullable
    String path();

    /**
     * 显示的名字
     */
    @Nullable
    String name();

    /**
     * 后端qpi时使用 get put post delete 等
     */
    @Nullable
    String action();

    Type type();
    /**
     * 父资源
     */
    @Nullable
    @ManyToOne
    @JoinColumn(foreignKeyType = ForeignKeyType.FAKE)
    Resources parent();

    enum Type {
        /**
         * 前端视图文件
         */
        VIEW,
        /**
         * 后端接口
         */
        ROUTER,
        /**
         * 前端按钮
         */
        BUTTON,
        /**
         * 前端菜单
         */
        MENU
    }

}
