plugins {
    java
    id("java-library")
//    alias(plugins.plugins.spring.springframework.boot)
//    alias(plugins.plugins.spring.dependency.management)
}


group = "com.rerubbish"
version = "0.0.1-SNAPSHOT"

subprojects {
    apply(plugin = "java")
    apply(plugin = "java-library")
    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }
}

allprojects {
    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }
}