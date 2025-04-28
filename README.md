# Simple ReMake Admin

基于较新技术、简单、更少的魔法、更少依赖、升级迁移容易的现代化可使用kotlin的rbac admin框架后端。

## 预定的技术选型

- JDK 17+
- kotlin 2.0+
- 框架使用spring boot 3.4
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
- sms
    - [ ] 阿里云sms
    - [ ] 腾讯云sms
    - [ ] 华为云sms
- [ ] 电子邮件
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
- [sms](./sms/README.md)
- [system](./system/README.md)
- [user](./user/README.md)

## 依赖管理

可以参考这篇blog[在国内如何更优雅的使用Gradle](https://blog.kxxnzstdsw.com/posts/how-to-use-gradle-better/)

## 统一的异常处理

### 可抛出异常

#### 业务异常

- 业务异实现[BusinessException](common/src/main/java/com/sreMake/common/exception/BusinessException.java)
-
默认实现业务异常[DefaultBusinessException](common/src/main/java/com/sreMake/common/exception/business/DefaultBusinessException.java)

#### 其他异常

- 如校验错误等

### 不可抛出异常

- 内部异常如sql异常io异常等

## 监控

### prometheus

使用prometheus添加 `http://localhost:8080/actuator/prometheus` 订阅，只有生产环境需要用户认证。

## 构建

### jar

``` shell
gradle --refresh-dependencies && gradle build
```

### docker

```shell
docker build -t srm-admin .
```

## openapi

启动项目后访问这个地址 `http://localhost:8080/openapi.html` ，只有非生产环境可以访问。

## commit

| emoji      | emoji代码                  | commit说明        |
|------------|--------------------------|-----------------|
| 🎨 (调色板)   | art                      | 改进代码结构/代码格式     |
| ⚡️ (闪电)    | zap                      | 提升性能            |
| 🐎 (赛马)    | racehorse                | 	提升性能           |
| 🔥 (火焰)    | fire                     | 移除代码或文件         |
| 🐛 (bug)   | bug                      | 修复 bug          |
| 🚑 (急救车)   | ambulance                | 	重要补丁           |
| ✨ (火花)     | sparkles                 | 引入新功能           |
| 📝 (铅笔)    | pencil                   | 撰写文档            |
| 🚀 (火箭)    | rocket                   | 部署功能            |
| 💄 (口红)    | lipstick                 | 更新 UI 和样式文件     |
| 🎉 (庆祝)    | tada                     | 初次提交            |
| ✅ (白色复选框)  | white_check_mark         | 增加测试            |
| 🔒 (锁)     | lock                     | 修复安全问题          |
| 🍎 (苹果)    | apple                    | 修复 macOS 下的问题   |
| 🐧 (企鹅)    | penguin                  | 修复 Linux 下的问题   |
| 🏁 (旗帜)    | checked_flag             | 修复 Windows 下的问题 |
| 🔖 (书签)    | bookmark                 | 发行/版本标签         |
| 🚨 (警车灯)   | rotating_light           | 移除 linter 警告    |
| 🚧 (施工)    | construction             | 工作进行中           |
| 💚 (绿心)    | green_heart              | 修复 CI 构建问题      |
| ⬇️ (下降箭头)  | arrow_down               | 降级依赖            |
| ⬆️ (上升箭头)  | arrow_up                 | 升级依赖            |
| 👷 (工人)    | construction_worker      | 添加 CI 构建系统      |
| 📈 (上升趋势图) | chart_with_upwards_trend | 添加分析或跟踪代码       |
| 🔨 (锤子)    | hammer                   | 重大重构            |
| ➖ (减号)     | heavy_minus_sign         | 减少一个依赖          |
| 🐳 (鲸鱼)    | whale                    | 相关工作            |
| ➕ (加号)     | heavy_plus_sign          | 增加一个依赖          |
| 🔧 (扳手)    | wrench                   | 修改配置文件          |
| 🌐 (地球)    | globe_with_meridians     | 国际化与本地化         |
| ✏️ (铅笔)    | pencil2                  | 修复 typo         |
