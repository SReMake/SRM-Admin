rootProject.name = "Jasmine-admin"

val urlMaps = mapOf(
    "https://repo.maven.apache.org/maven2" to "https://maven.aliyun.com/repository/public",
    "https://repo1.maven.apache.org/maven2" to "https://mirrors.cloud.tencent.com/nexus/repository/maven-public/",
    "https://dl.google.com/dl/android/maven2" to "https://mirrors.cloud.tencent.com/nexus/repository/maven-public/",
    "https://plugins.gradle.org/m2" to "https://maven.aliyun.com/repository/gradle-plugin"
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
    versionCatalogs {

        create("plugins") {
            plugin("spring-boot", "org.springframework.boot").version("3.4.1")
//            plugin("spring-dependency-management", "io.spring.dependency-management").version("1.1.6")
            plugin("spring-dependency-management", "io.spring.dependency-management").version("latest.release")
        }
        create("development") {
            version("spring", "3.4.1")
            library("spring-boot-devtools", "org.springframework.boot", "spring-boot-devtools").versionRef("spring")
        }
        create("test") {
            version("spring-boot", "3.4.1")
            version("spring-security", "6.4.2")

            library(
                "spring-boot-starter-test",
                "org.springframework.boot",
                "spring-boot-starter-test"
            ).versionRef("spring-boot")
            library(
                "spring-security-test",
                "org.springframework.security",
                "spring-security-test"
            ).versionRef("spring-security")
            library(
                "junit-platform-launcher",
                "org.junit.platform",
                "junit-platform-launcher"
            ).version("latest.release")

        }
        create("starter") {
            version("spring-boot", "3.4.1")
            library(
                "spring-boot-starter-data-redis",
                "org.springframework.boot",
                "spring-boot-starter-data-redis"
            ).versionRef("spring-boot")
            library(
                "spring-boot-starter-quartz",
                "org.springframework.boot",
                "spring-boot-starter-quartz"
            ).versionRef("spring-boot")
            library(
                "spring-boot-starter-security",
                "org.springframework.boot",
                "spring-boot-starter-security"
            ).versionRef("spring-boot")
            library(
                "spring-boot-starter-web",
                "org.springframework.boot",
                "spring-boot-starter-web"
            ).versionRef("spring-boot")
        }
        create("jdbcDriver") {
            library("mysql", "com.mysql", "mysql-connector-j").version("latest.release")
            library("postgresql", "org.postgresql", "postgresql").version("latest.release")
        }
        create("spring") {
            version("spring-boot", "3.4.1")
            library(
                "spring-boot-configuration-processor",
                "org.springframework.boot",
                "spring-boot-configuration-processor"
            ).versionRef("spring-boot")
        }
        create("apt"){
            library("lombok","org.projectlombok","lombok").version("latest.release")
        }
    }
}