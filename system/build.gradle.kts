group = "com.SReMake"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(project(":common"))
    implementation(project(":repository"))
    implementation(utils.hutool.all)
    implementation(starter.spring.boot.starter.web)
    implementation(starter.jimmer.spring.boot.starter)
    implementation(starter.spring.boot.starter.security)
    implementation(starter.spring.boot.starter.data.redis)
    implementation(casbin.jcasbin)
    runtimeOnly(utils.jimmer.client.swagger)
    annotationProcessor(aptAndKsp.jimmer)
    compileOnly(aptAndKsp.jimmer)
    annotationProcessor(aptAndKsp.lombok)
    compileOnly(aptAndKsp.lombok)
}