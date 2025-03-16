# SRM-Admin

清馨的admin框架，轻巧、简单、更少的魔法、更少依赖、升级迁移容易的现代化可使用kotlin的rbac admin框架后端。

## 预定的技术选型

- JDK 17
- 框架使用spring boot
- 权限使用Casbin
- 认证基于spring security的jwt
- orm
    - Jimmer
- 数据库
    - postgresql
    - redis
    - [ ] mysql
- 缓存
    - Spring Cache
- 对象存储
    - [ ] 阿里oss
    - [ ] 腾讯cos
    - [ ] 华为obs
- [ ] 文件系统
    - [ ] nfs
    - [ ] webdev
- 消息队列
    - [ ] RabbitMQ 可选
- 任务调度
    - quartz
    - [ ] mq
- 容器
    - docker
- 远程调用
    - [ ] GRPC

## 预定基础模块

- [app](./app/README.md)
- [common](./common/README.md)
- [model](./model/README.md)
- [repository](./repository/README.md)
- [scheduler](./scheduler/README.md)
- [security](./security/README.md)
- [system](./system/README.md)
- [user](./user/README.md)

## openapi

启动项目后访问这个地址`http://localhost:8080/openapi.html`
