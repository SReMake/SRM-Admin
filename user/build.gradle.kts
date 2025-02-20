group = "com.SReMake"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(project(":common"))
    implementation(project(":repository"))
    implementation(utils.hutool.all)
    implementation(starter.spring.boot.starter.web)
    implementation(starter.jimmer.spring.boot.starter)
    runtimeOnly(utils.jimmer.client.swagger)
    annotationProcessor(apt.lombok)
    compileOnly(apt.lombok)
}