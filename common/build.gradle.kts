group = "com.sreMake.common"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(utils.jjwt.api)
    implementation(starter.spring.boot.starter.web)
    implementation(starter.spring.boot.starter.data.redis)
    implementation(starter.spring.boot.starter.security)
    implementation(utils.hutool.all)

    api(starter.jimmer.spring.boot.starter)
    annotationProcessor(aptAndKsp.lombok)
    compileOnly(aptAndKsp.lombok)
}
