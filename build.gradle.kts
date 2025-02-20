plugins {
    java
    id("java-library")
}


group = "com.SReMake"
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
    tasks.withType<JavaCompile> {
        options.compilerArgs.add("-parameters")
    }
    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }
}