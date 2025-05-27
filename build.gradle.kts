plugins {
    java
    alias(plugins.plugins.kotlin.jvm)
    id("java-library")
}


group = "com.sreMake"
version = "0.0.1-SNAPSHOT"

subprojects {
    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    kotlin {
        jvmToolchain(21)
    }
    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }
}


allprojects {
    tasks.withType<JavaCompile> {
        options.compilerArgs.add("-parameters")
    }
    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }
}