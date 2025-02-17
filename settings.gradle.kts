rootProject.name = "SRM-Admin"


val urlMaps = mapOf(
    "https://repo.maven.apache.org/maven2" to "https://maven.aliyun.com/repository/public",
    "https://repo1.maven.apache.org/maven2" to "https://mirrors.cloud.tencent.com/nexus/repository/maven-public/",
    "https://dl.google.com/dl/android/maven2" to "https://mirrors.cloud.tencent.com/nexus/repository/maven-public/",
//    "https://plugins.gradle.org/m2" to "https://maven.aliyun.com/repository/gradle-plugin"
)

fun RepositoryHandler.enableMirror() {
    all {
        if (this is MavenArtifactRepository) {
            val originalUrl = this.url.toString().removeSuffix("/")
            urlMaps[originalUrl]?.let {
                logger.lifecycle("Repository[$url] is mirrored to $it")
                this.setUrl(it)
            }
        }
    }
}
gradle.allprojects {
    repositories {
        mavenCentral()
        maven("https://maven.aliyun.com/repository/gradle-plugin")

    }
    buildscript {
        repositories.enableMirror()
    }
    repositories.enableMirror()
}

gradle.beforeSettings {
    pluginManagement.repositories.enableMirror()
    dependencyResolutionManagement.repositories.enableMirror()
}

dependencyResolutionManagement {
    val springBootVersion = "3.4.1"
    val springSecurityVersion = "6.4.2"
    val jimmerVersion = "0.9.48"
    versionCatalogs {
        create("plugins") {
            plugin("spring-springframework-boot", "org.springframework.boot").version(springBootVersion)
            plugin("spring-dependency-management", "io.spring.dependency-management").version("latest.release")
        }
        create("development") {
            library("spring-boot-devtools", "org.springframework.boot", "spring-boot-devtools").version(
                springBootVersion
            )
        }
        create("test") {
            library(
                "spring-boot-starter-test", "org.springframework.boot", "spring-boot-starter-test"
            ).version(springBootVersion)
            library(
                "spring-security-test", "org.springframework.security", "spring-security-test"
            ).version(springSecurityVersion)
            library(
                "junit-platform-launcher", "org.junit.platform", "junit-platform-launcher"
            ).version("latest.release")

        }
        create("starter") {
            library(
                "spring-boot-starter-data-redis", "org.springframework.boot", "spring-boot-starter-data-redis"
            ).version(springBootVersion)
            library(
                "spring-boot-starter-quartz", "org.springframework.boot", "spring-boot-starter-quartz"
            ).version(springBootVersion)
            library(
                "spring-boot-starter-security", "org.springframework.boot", "spring-boot-starter-security"
            ).version(springBootVersion)
            library(
                "spring-boot-starter-web", "org.springframework.boot", "spring-boot-starter-web"
            ).version(springBootVersion)
            library("jimmer-spring-boot-starter", "org.babyfish.jimmer", "jimmer-spring-boot-starter").version(
                jimmerVersion
            )
        }
        create("jdbcDriver") {
            library("mysql", "com.mysql", "mysql-connector-j").version("latest.release")
            library("postgresql", "org.postgresql", "postgresql").version("latest.release")
            library("h2", "com.h2database", "h2").version("latest.release")
        }
        create("spring") {
            library(
                "spring-boot-configuration-processor", "org.springframework.boot", "spring-boot-configuration-processor"
            ).version(springBootVersion)
        }
        create("apt") {
            library("lombok", "org.projectlombok", "lombok").version("latest.release")
            library("jimmer", "org.babyfish.jimmer", "jimmer-apt").version(jimmerVersion)
        }
        create("utils") {
            library("jimmer-client-swagger", "org.babyfish.jimmer", "jimmer-client-swagger").version(jimmerVersion)
            library("hutool-all", "cn.hutool", "hutool-all").version("5.8.16")
        }
        create("oss") {
        }
        create("jimmer") {
            library("sql", "org.babyfish.jimmer", "jimmer-sql").version(jimmerVersion)
        }
    }
}
include("model")
include("repository")
include("user")
include("system")
include("app")
include("scheduler")
include("common")
