# system 模块

系统配置、管理和监控等功能。

- 系统设置
    - 站点信息配置
    - 参数配置
- 菜单管理
    - 菜单列表展示
    - 新增、编辑、删除菜单项
    - 菜单排序与状态管理
- 角色与权限管理
    - 角色列表展示（创建、编辑、删除角色）
    - 权限分配（全局权限、菜单权限、操作权限）
- 字典管理
    - 数据字典定义
    - 字典项的新增、编辑、删除
- 日志管理
    - 操作日志
    - 登录日志
    - 日志查询与导出
- 数据备份与恢复
    - 数据库备份
    - 数据恢复
- 系统监控
    - 普罗米修斯订阅地址 `http://localhost:8080/actuator/prometheus` 只有内网可以访问或者自行修改[ActuatorFilter](../security/src/main/java/com/sreMake/security/spring/ActuatorFilter.java)