# 入口模块

该模块整合了所有的模块，用于启动整个程序。
目前整个项目的配置都在[application.yaml](./src/main/resources/application.yaml)中

## 如何注册新模块

全局的JavaBean都是spring firework 在托管，所以只要在这个模块中添加依赖，就可以使用了。

1. 添加一个service的gradle模块
2. 在全局的[settings.gradle.kts](../settings.gradle.kts)中添加该模块
3. 然后在[build.gradle.kts](./build.gradle.kts)中添加这个模块的依赖


