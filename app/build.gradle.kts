group = "com.sreMake"
version = "0.0.1-SNAPSHOT"

plugins {
    java
    alias(plugins.plugins.spring.springframework.boot)
    alias(plugins.plugins.spring.dependency.management)
    alias(plugins.plugins.kotlin.spring)
    alias(plugins.plugins.kotlin.jvm)
    id("java-library")
}


dependencies {
    implementation(project(":common"))
    implementation(project(":security"))
    implementation(project(":user"))
    implementation(project(":system"))
    implementation(project(":scheduler"))
    implementation(platform(micrometer.bom))
    implementation(micrometer.registry.prometheus)
    implementation(starter.spring.boot.starter.actuator)
    implementation(starter.spring.boot.starter.web)
    implementation(starter.jimmer.spring.boot.starter)


    runtimeOnly(utils.jimmer.client.swagger)
    runtimeOnly(jdbcDriver.mysql)
    runtimeOnly(jdbcDriver.postgresql)
    runtimeOnly(jdbcDriver.h2)

    annotationProcessor(spring.spring.boot.configuration.processor)
    annotationProcessor(aptAndKsp.lombok)
    annotationProcessor(aptAndKsp.jimmer)

    developmentOnly(development.spring.boot.devtools)

    compileOnly(aptAndKsp.jimmer)
    compileOnly(aptAndKsp.lombok)

    testRuntimeOnly(test.junit.platform.launcher)
    testImplementation(test.spring.boot.starter.test)
    testImplementation(test.spring.security.test)


}