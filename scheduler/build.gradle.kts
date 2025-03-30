group = "com.sreMake"
version = "0.0.1-SNAPSHOT"


dependencies {
    implementation(project(":common"))
    implementation(project(":repository"))
    implementation(utils.hutool.all)
    implementation(starter.spring.boot.starter.quartz)
    implementation(starter.spring.boot.starter.data.jdbc)
    implementation(starter.spring.boot.starter.web)
    implementation(starter.spring.boot.starter.aop)
    runtimeOnly(utils.jimmer.client.swagger)
    annotationProcessor(aptAndKsp.jimmer)
    annotationProcessor(aptAndKsp.lombok)
    compileOnly(aptAndKsp.lombok)
}