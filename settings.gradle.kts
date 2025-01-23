rootProject.name = "Jasmine-admin"


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
    val jimmerVersion = "0.9.47"
    versionCatalogs {
        create("plugins") {
            plugin("spring-springframework-boot", "org.springframework.boot").version(springBootVersion)
//            plugin("spring-dependency-management", "io.spring.dependency-management").version("1.1.6")
            plugin("spring-dependency-management", "io.spring.dependency-management").version("latest.release")
        }
        create("development") {
            version("spring", springBootVersion)
            library("spring-boot-devtools", "org.springframework.boot", "spring-boot-devtools").versionRef("spring")
        }
        create("test") {
            version("spring-boot", springBootVersion)
            version("spring-security", springSecurityVersion)

            library(
                "spring-boot-starter-test", "org.springframework.boot", "spring-boot-starter-test"
            ).versionRef("spring-boot")
            library(
                "spring-security-test", "org.springframework.security", "spring-security-test"
            ).versionRef("spring-security")
            library(
                "junit-platform-launcher", "org.junit.platform", "junit-platform-launcher"
            ).version("latest.release")

        }
        create("starter") {
            version("spring-boot", springBootVersion)
            library(
                "spring-boot-starter-data-redis", "org.springframework.boot", "spring-boot-starter-data-redis"
            ).versionRef("spring-boot")
            library(
                "spring-boot-starter-quartz", "org.springframework.boot", "spring-boot-starter-quartz"
            ).versionRef("spring-boot")
            library(
                "spring-boot-starter-security", "org.springframework.boot", "spring-boot-starter-security"
            ).versionRef("spring-boot")
            library(
                "spring-boot-starter-web", "org.springframework.boot", "spring-boot-starter-web"
            ).versionRef("spring-boot")
            library("jimmer-spring-boot-starter", "org.babyfish.jimmer", "jimmer-spring-boot-starter").version(
                jimmerVersion
            )
        }
        create("jdbcDriver") {
            library("mysql", "com.mysql", "mysql-connector-j").version("latest.release")
            library("postgresql", "org.postgresql", "postgresql").version("latest.release")
        }
        create("spring") {
            version("spring-boot", springBootVersion)
            library(
                "spring-boot-configuration-processor", "org.springframework.boot", "spring-boot-configuration-processor"
            ).versionRef("spring-boot")
        }
        create("apt") {
            library("lombok", "org.projectlombok", "lombok").version("latest.release")
            library("jimmer", "org.babyfish.jimmer", "jimmer-apt").version(jimmerVersion)
        }
        create("utils") {
            library("jimmer-client-swagger", "org.babyfish.jimmer", "jimmer-client-swagger").version(jimmerVersion)
            library("hutool-all", "cn.hutool", "hutool-all").version("5.8.16")
        }
    }
}