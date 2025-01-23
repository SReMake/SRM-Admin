plugins {
    java
    alias(plugins.plugins.spring.boot)
    alias(plugins.plugins.spring.dependency.management)
}

group = "com.langbiantianya"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(starter.spring.boot.starter.data.redis)
    implementation(starter.spring.boot.starter.quartz)
    implementation(starter.spring.boot.starter.security)
    implementation(starter.spring.boot.starter.web)
    compileOnly(apt.lombok)
    developmentOnly(development.spring.boot.devtools)
    runtimeOnly(jdbcDriver.mysql)
    runtimeOnly(jdbcDriver.postgresql)
    annotationProcessor(spring.spring.boot.configuration.processor)
    annotationProcessor(apt.lombok)
    testImplementation(test.spring.boot.starter.test)
    testImplementation(test.spring.security.test)
    testRuntimeOnly(test.junit.platform.launcher)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
