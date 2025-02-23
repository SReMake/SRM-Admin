plugins {
    alias(plugins.plugins.spring.springframework.boot)
    alias(plugins.plugins.spring.dependency.management)
}

group = "com.SReMake"
version = "0.0.1-SNAPSHOT"



dependencies {
    implementation(project(":common"))
    implementation(project(":security"))
    implementation(project(":user"))
    implementation(project(":system"))

    implementation(starter.spring.boot.starter.web)
    implementation(starter.jimmer.spring.boot.starter)
    implementation(starter.spring.boot.starter.data.redis)
    implementation(starter.spring.boot.starter.quartz)
//    implementation(starter.spring.boot.starter.security)
    implementation(utils.hutool.all)


    runtimeOnly(utils.jimmer.client.swagger)
    runtimeOnly(jdbcDriver.mysql)
    runtimeOnly(jdbcDriver.postgresql)
    runtimeOnly(jdbcDriver.h2)

    annotationProcessor(spring.spring.boot.configuration.processor)
    annotationProcessor(apt.lombok)
    annotationProcessor(apt.jimmer)

    developmentOnly(development.spring.boot.devtools)

    compileOnly(apt.lombok)

    testRuntimeOnly(test.junit.platform.launcher)
    testImplementation(test.spring.boot.starter.test)
    testImplementation(test.spring.security.test)


}

tasks.test {
    useJUnitPlatform()
}