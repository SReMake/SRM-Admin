plugins {
    java
    alias(plugins.plugins.spring.springframework.boot)
    alias(plugins.plugins.spring.dependency.management)
    alias(plugins.plugins.ksp)
    alias(plugins.plugins.kotlin.spring)
    alias(plugins.plugins.kotlin.jvm)
    id("java-library")
}


group = "com.SReMake"
version = "0.0.1-SNAPSHOT"

subprojects {
    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "com.google.devtools.ksp")
    kotlin {
        jvmToolchain(17)
    }
    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }
}


allprojects {
    kotlin {
        sourceSets.main {
            kotlin.srcDir("build/generated/ksp/main/kotlin")
        }
    }
    ksp {

        sourceSets.main {
            kotlin.srcDir("src/main/kotlin")
        }
        sourceSets.test {
            kotlin.srcDir("src/test/kotlin")
        }
    }
    tasks.withType<JavaCompile> {
        options.compilerArgs.add("-parameters")
    }
    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }
}