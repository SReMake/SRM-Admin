group = "com.SReMake"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(project(":common"))
    implementation(project(":repository"))
    implementation(utils.hutool.all)
    implementation(starter.spring.boot.starter.web)
    implementation(starter.spring.boot.starter.security)
    implementation(starter.spring.boot.starter.data.jdbc)
    implementation(casbin.jcasbin)
    implementation(casbin.jdbc.adapter)
    annotationProcessor(apt.lombok)
    compileOnly(apt.lombok)
}