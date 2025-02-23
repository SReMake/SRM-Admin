group = "com.SReMake.common"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(utils.jjwt.api)
    implementation(starter.spring.boot.starter.web)
    implementation(starter.spring.boot.starter.security)
    api(starter.jimmer.spring.boot.starter)
    annotationProcessor(apt.lombok)
    compileOnly(apt.lombok)
}
