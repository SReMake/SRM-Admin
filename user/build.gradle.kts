group = "com.SReMake"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(project(":common"))
    implementation(project(":security"))
    implementation(project(":repository"))
    implementation(utils.hutool.all)
    implementation(starter.spring.boot.starter.web)
    implementation(starter.jimmer.spring.boot.starter)
    implementation(starter.spring.boot.starter.security)
    implementation(starter.spring.boot.starter.data.redis)
    implementation(casbin.jcasbin)
    runtimeOnly(utils.jimmer.client.swagger)
    annotationProcessor(apt.jimmer)
    compileOnly(apt.jimmer)
    annotationProcessor(apt.lombok)
    compileOnly(apt.lombok)
}